package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.gnss.GnssUtils
import aero.geosystems.gnss.SatSystem
import java.nio.ByteBuffer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class GloMsmEpoch(val dow: Int, val epochTime: Long) {
	override fun toString(): String {
		return "${dow}d${epochTime}ms(${msOfDayToString(epochTime)})"
	}
}

abstract class RtcmMsmGlonassDef<BINDING : RtcmMsmCommon<GloMsmEpoch, BINDING>>(mid_const: Int) :
		RtcmMsmCommonDef<GloMsmEpoch, BINDING>(SatSystem.GLONASS, mid_const - 1080, mid_const) {
	inner class GloMsmEpochMember : NumberMember<GloMsmEpoch>(30), ReadWriteProperty<StructBinding, GloMsmEpoch> {
		override fun getValue(binding: StructBinding): GloMsmEpoch {
			val d = getUnsigned(binding)
			return GloMsmEpoch(d.shr(27).and(1.shl(3) - 1).toInt(), d.and(1.shl(27) - 1))
		}

		override fun setValue(thisRef: StructBinding, property: KProperty<*>, value: GloMsmEpoch) {
			setValue(value.epochTime.or(value.dow.shl(27).toLong()), thisRef)
		}
	}

	fun DF416_DF034(): GloMsmEpochMember = GloMsmEpochMember()

	override fun gnss_epoch_def_gen(): ReadWriteProperty<StructBinding, GloMsmEpoch> = DF416_DF034()
	override fun getGpstime(epoch_time: GloMsmEpoch, ref_gpstime: Long): Long {
		return GnssUtils.glodms2gpstime(epoch_time.dow,epoch_time.epochTime,ref_gpstime)
	}
}

class Rtcm1081(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1081>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1081>(1081) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1081 = Rtcm1081(bb, structOffset)
	}
}

class Rtcm1082(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1082>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1082>(1082) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1082 = Rtcm1082(bb, structOffset)
	}
}

class Rtcm1083(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1083>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1083>(1083) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1083 = Rtcm1083(bb, structOffset)
	}
}

class Rtcm1084(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1084>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1084>(1084) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1084 = Rtcm1084(bb, structOffset)
	}
}

class Rtcm1085(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1085>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1085>(1085) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1085 = Rtcm1085(bb, structOffset)
	}
}

class Rtcm1086(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1086>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1086>(1086) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1086 = Rtcm1086(bb, structOffset)
	}
}

class Rtcm1087(bb: ByteBuffer, offset: Int=0) : RtcmMsmCommon<GloMsmEpoch, Rtcm1087>(Companion, bb, offset) {
	companion object : RtcmMsmGlonassDef<Rtcm1087>(1087) {
		override fun binding(bb: ByteBuffer, structOffset: Int): Rtcm1087 = Rtcm1087(bb, structOffset)
	}
}