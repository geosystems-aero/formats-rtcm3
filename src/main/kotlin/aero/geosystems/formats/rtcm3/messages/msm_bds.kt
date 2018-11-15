package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.gnss.GnssUtils
import aero.geosystems.gnss.SatSystem
import java.nio.ByteBuffer
import kotlin.properties.ReadWriteProperty

abstract class RtcmMsmBeidouDef<BINDING : RtcmMsmCommon<Long, BINDING>>(mid_const: Int) :
		RtcmMsmCommonDef<Long, BINDING>(SatSystem.BDS, mid_const - 1120, mid_const) {
	override fun gnss_epoch_def_gen(): ReadWriteProperty<StructBinding, Long> = DF427()
	override fun getGpstime(epoch_time: Long, ref_gpstime: Long): Long {
		return GnssUtils.addGuessedWeek(ref_gpstime,GnssUtils.bdt2gps(epoch_time))
	}
}

class Rtcm1121(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1121>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1121>(1121) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1121 = Rtcm1121(bb, structOffset)
	}
}

class Rtcm1122(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1122>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1122>(1122) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1122 = Rtcm1122(bb, structOffset)
	}
}

class Rtcm1123(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1123>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1123>(1123) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1123 = Rtcm1123(bb, structOffset)
	}
}

class Rtcm1124(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1124>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1124>(1124) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1124 = Rtcm1124(bb, structOffset)
	}
}

class Rtcm1125(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1125>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1125>(1125) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1125 = Rtcm1125(bb, structOffset)
	}
}

class Rtcm1126(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1126>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1126>(1126) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1126 = Rtcm1126(bb, structOffset)
	}
}

class Rtcm1127(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1127>(Companion, bb, offset) {
	companion object : RtcmMsmBeidouDef<Rtcm1127>(1127) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1127 = Rtcm1127(bb, structOffset)
	}
}