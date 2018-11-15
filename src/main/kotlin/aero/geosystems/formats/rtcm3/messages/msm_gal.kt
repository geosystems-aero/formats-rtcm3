package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.gnss.GnssUtils
import aero.geosystems.gnss.SatSystem
import java.nio.ByteBuffer
import kotlin.properties.ReadWriteProperty

abstract class RtcmMsmGalileoDef<BINDING : RtcmMsmCommon<Long, BINDING>>(mid_const: Int) :
		RtcmMsmCommonDef<Long, BINDING>(SatSystem.GALILEO, mid_const - 1090, mid_const) {
	override fun gnss_epoch_def_gen(): ReadWriteProperty<StructBinding, Long> = DF248()
	override fun getGpstime(epoch_time: Long, ref_gpstime: Long): Long {
		return GnssUtils.addGuessedWeek(ref_gpstime,epoch_time)
	}
}

class Rtcm1091(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1091>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1091>(1091) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1091 = Rtcm1091(bb, structOffset)
	}
}

class Rtcm1092(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1092>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1092>(1092) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1092 = Rtcm1092(bb, structOffset)
	}
}

class Rtcm1093(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1093>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1093>(1093) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1093 = Rtcm1093(bb, structOffset)
	}
}

class Rtcm1094(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1094>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1094>(1094) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1094 = Rtcm1094(bb, structOffset)
	}
}

class Rtcm1095(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1095>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1095>(1095) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1095 = Rtcm1095(bb, structOffset)
	}
}

class Rtcm1096(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1096>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1096>(1096) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1096 = Rtcm1096(bb, structOffset)
	}
}

class Rtcm1097(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<Long, Rtcm1097>(Companion, bb, offset) {
	companion object : RtcmMsmGalileoDef<Rtcm1097>(1097) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1097 = Rtcm1097(bb, structOffset)
	}
}