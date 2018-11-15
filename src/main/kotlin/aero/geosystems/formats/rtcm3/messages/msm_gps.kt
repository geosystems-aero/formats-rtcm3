package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.gnss.GnssUtils
import aero.geosystems.gnss.SatSystem
import java.nio.ByteBuffer
import kotlin.properties.ReadWriteProperty

abstract class RtcmMsmGpsDef<BINDING : RtcmMsmCommon<Long, BINDING>>(mid_const: Int) :
		RtcmMsmCommonDef<Long, BINDING>(SatSystem.GPS, mid_const - 1070, mid_const) {
	override fun gnss_epoch_def_gen(): ReadWriteProperty<StructBinding, Long> = DF004()
	override fun getGpstime(epoch_time: Long, ref_gpstime: Long): Long {
		return GnssUtils.addGuessedWeek(ref_gpstime,epoch_time)
	}
}

class Rtcm1071(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1071>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1071>(1071) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1071 = Rtcm1071(bb, structOffset)
	}
}

class Rtcm1072(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1072>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1072>(1072) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1072 = Rtcm1072(bb, structOffset)
	}
}

class Rtcm1073(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1073>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1073>(1073) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1073 = Rtcm1073(bb, structOffset)
	}
}

class Rtcm1074(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1074>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1074>(1074) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1074 = Rtcm1074(bb, structOffset)
	}
}

class Rtcm1075(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1075>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1075>(1075) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1075 = Rtcm1075(bb, structOffset)
	}
}

class Rtcm1076(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1076>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1076>(1076) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1076 = Rtcm1076(bb, structOffset)
	}
}

class Rtcm1077(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1077>(Companion, bb, offset) {
	companion object : RtcmMsmGpsDef<Rtcm1077>(1077) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1077 = Rtcm1077(bb, structOffset)
	}
}