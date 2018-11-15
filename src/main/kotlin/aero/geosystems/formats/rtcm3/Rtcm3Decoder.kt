package aero.geosystems.formats.rtcm3

import aero.geosystems.formats.AbstractGnssDecoder
import aero.geosystems.formats.IGnssDataConsumer
import aero.geosystems.formats.NopGnssConsumer
import aero.geosystems.formats.rtcm3.messages.RtcmCommon_1001_1004
import aero.geosystems.formats.rtcm3.messages.RtcmCommon_1009_1012
import aero.geosystems.formats.rtcm3.messages.RtcmMsmCommon
import aero.geosystems.gnss.gloms2gpstime
import aero.geosystems.gnss.gpstimeWithGuessedWeeks
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

/**
 * Created by aimozg on 30.01.2017.
 * Confidential.
 */
class Rtcm3Decoder(var refGpsTime: Long,
                   sink: IGnssDataConsumer<Rtcm3Message> = NopGnssConsumer) :
		AbstractGnssDecoder<Rtcm3Message>(6, 1, sink){
	private var header = Rtcm3UnknownMessage(headerBuffer)

	override fun resetHeader() {
		super.resetHeader()
		header.clearCaches()
	}

	override fun syncByte(idx: Int): Byte {
		return PREAMBLE_VALUE.toByte()
	}

	override fun checkMinimalHeader(): Boolean {
		return header.message_id>1000
	}

	override fun allocateMessageBuffer(): ByteBuffer {
		return ByteBuffer.allocateDirect(header.totalMessageSize).order(ByteOrder.BIG_ENDIAN);
	}

	override fun crcGood(): Boolean {
		val message = Rtcm3UnknownMessage(messageBuffer)
		if (messageBuffer.limit()<message.totalMessageSize) return false
		val has = message.crc
		val calc = message.calcCrc()
		return has == calc
	}

	override fun completeAndConsumeMessage() {
		var message: Rtcm3Message = Rtcm3UnknownMessage(messageBuffer)
		val mid = message.message_id
		val clazz = Rtcm3MessageIDs[mid]
		if (clazz != null) message = clazz.binding(messageBuffer,0)
		val gpstime = when(message){
			is RtcmCommon_1001_1004<*> -> message.gps_epoch.gpstimeWithGuessedWeeks(refGpsTime)
			is RtcmCommon_1009_1012<*> -> message.glo_epoch.toLong().gloms2gpstime(refGpsTime)
			is RtcmMsmCommon<*,*> -> message.getGpstime(refGpsTime)
			else -> null
		}
		if (gpstime != null) {
			if (logger?.isLoggable(Level.FINEST)?:false) logger?.log(Level.FINEST,"$mid | $refGpsTime -> $gpstime")
			refGpsTime = gpstime
		}
		/*
		if (mid == 1013) {
			refGpstime = GnssUtils.addGuessedWeek(
					GnssUtils.mjdToGpstime((message.body as Body1013).mjd_number.get(),refGpstime%GnssUtils.MS_IN_DAY),
					GnssUtils.extractMs(refGpstime));
		}

		 */
		val type:Int = when {
			OBSERVATION_MIDS.contains(mid) -> IGnssDataConsumer.TYPE_OBSERVATIONS
			EPHEMERIS_MIDS.contains(mid) -> IGnssDataConsumer.TYPE_EPHEMERIS
			else -> IGnssDataConsumer.TYPE_OTHER_DATA
		}
		sink.consume(message,messageBuffer,gpstime,type)
	}
	companion object {
		private val logger: Logger? = LogManager.getLogManager().getLogger("aero.geosystems.formats.rtcm3.Rtcm3Decoder")
	}
}