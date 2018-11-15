package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.gnss.GnssUtils
import aero.geosystems.gnss.SatSystem
import java.nio.ByteBuffer
import kotlin.properties.ReadWriteProperty

abstract class RtcmMsmQzssDef<BINDING : RtcmMsmCommon<Long, BINDING>>(mid_const: Int) :
		RtcmMsmCommonDef<Long, BINDING>(SatSystem.QZSS, mid_const - 1110, mid_const) {
	override fun gnss_epoch_def_gen(): ReadWriteProperty<StructBinding, Long> = DF004()
	override fun getGpstime(epoch_time: Long, ref_gpstime: Long): Long {
		return GnssUtils.addGuessedWeek(ref_gpstime,epoch_time)
	}
}

class Rtcm1111(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1111>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1111>(1111) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1111 = Rtcm1111(bb, structOffset)
	}
}

class Rtcm1112(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1112>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1112>(1112) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1112 = Rtcm1112(bb, structOffset)
	}
}

class Rtcm1113(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1113>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1113>(1113) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1113 = Rtcm1113(bb, structOffset)
	}
}

class Rtcm1114(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1114>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1114>(1114) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1114 = Rtcm1114(bb, structOffset)
	}
}

class Rtcm1115(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1115>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1115>(1115) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1115 = Rtcm1115(bb, structOffset)
	}
}

class Rtcm1116(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1116>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1116>(1116) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1116 = Rtcm1116(bb, structOffset)
	}
}

class Rtcm1117(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1117>(Companion, bb, offset) {
	companion object : RtcmMsmQzssDef<Rtcm1117>(1117) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1117 = Rtcm1117(bb, structOffset)
	}
}