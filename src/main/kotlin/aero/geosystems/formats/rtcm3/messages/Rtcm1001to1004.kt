package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.formats.StructDef
import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import aero.geosystems.formats.rtcm3.Rtcm3StructDef
import aero.geosystems.formats.rtcm3.Rtcm3StructDef.Companion.LIGHTMS
import aero.geosystems.gnss.GnssConstants
import java.nio.ByteBuffer
import java.util.*

abstract class RtcmCommonDef_1001_1004<BINDING : RtcmCommon_1001_1004<SB>, out SB:RtcmSatCommon_1001_1004>(
		satdef:RtcmSatCommonDef_1001_1004<SB>,mid_const: Int) : Rtcm3MessageDef<BINDING>(mid_const) {
	val refstn_id_def = DF003()
	val gps_epoch_def = DF004()
	val synch_gnss_def = DF005()
	val num_sat_def = DF006()
	val gps_div_smooth_def = DF007()
	val gps_smooth_int_def = DF008()
	val sats_def = StructArrayMember(satdef, num_sat_def)
}

abstract class RtcmCommon_1001_1004<out SB:RtcmSatCommon_1001_1004>(
		override final val def: RtcmCommonDef_1001_1004<*,SB>,
		bb: ByteBuffer,
		offset: Int) : Rtcm3Message(def, bb, offset) {
	var refstn_id: Int by def.refstn_id_def
	var gps_epoch: Long by def.gps_epoch_def
	var synch_gnss: Boolean by def.synch_gnss_def
	var num_sat: Int by def.num_sat_def
	var gps_div_smooth: Boolean by def.gps_div_smooth_def
	var gps_smooth_int: Int by def.gps_smooth_int_def

	val sats: List<SB> by def.sats_def
	fun getSat(index: Int) = def.sats_def.getItem(this, index)

	override fun bodyToString(): String {
		return "$refstn_id,$gps_epoch,$synch_gnss,$num_sat,$gps_div_smooth,$gps_smooth_int" +
				if (num_sat == 0) "" else ";${sats.joinToString(";")}"
	}
}

abstract class RtcmSatCommonDef_1001_1004<SB : RtcmSatCommon_1001_1004>(val hasamb: Boolean, val hasL2: Boolean) : Rtcm3StructDef<SB>() {
	val hascnr = hasamb
	val sat_id_def = DF009()
	val l1code_ind_def = DF010()
	val l1psr_def = DF011()
	val l1phrl1psr_def = DF012()
	val l1locktime_ind_def = DF013()
	val l1psr_amb_def = if (hasamb) DF014() else null
	val l1cnr_def = if (hascnr) DF015() else null
	val l2code_ind_def = if (hasL2) DF016() else null
	val l2l1psrdiff_def = if (hasL2) DF017() else null
	val l2phrl1psr_def = if (hasL2) DF018() else null
	val l2locktime_ind_def = if (hasL2) DF019() else null
	val l2cnr_def = if (hascnr && hasL2) DF020() else null
}

abstract class Rtcm3v0RtkSatCommon(val hasL2: Boolean, val hasamb: Boolean, val hascnr:Boolean, val amb_mod:Double,
                                   def_: StructDef<*>, bb:ByteBuffer, offset:Int): StructBinding(def_,bb,offset) {
	abstract val waveL1: Double
	abstract val waveL2: Double
	abstract val min_l1phrl1psrDouble: Double
	abstract val max_l1phrl1psrDouble: Double
	abstract val min_l2phrl1psrDouble: Double
	abstract val max_l2phrl1psrDouble: Double

	abstract var l1psr: Double
	abstract var l1psr_amb: Double
	abstract var l1psr_amb_raw: Long
	abstract var l1phrl1psr: Double
	abstract var l1phrl1psr_raw: Long
	abstract var l1locktime_ind: Int
	abstract var l1cnr: Double
	abstract var l2l1psrdiff: Double
	abstract var l2l1psrdiff_raw: Long
	abstract var l2phrl1psr: Double
	abstract var l2phrl1psr_raw: Long
	abstract var l2locktime_ind: Int
	abstract var l2cnr: Double

	fun extractL1PsrAmb(l1psr:Double) = Math.floor(l1psr/amb_mod)*amb_mod

	fun getL1Pseudorange(l1psr_amb: Double): Double = l1psr + l1psr_amb

	fun setL1Pseudorange(@Suppress("UNUSED_PARAMETER") l1psr_amb: Double, value: Double) {
		l1psr = value % amb_mod
		if (hasamb) l1psr_amb_raw = (value / amb_mod).toLong()
	}

	fun getL1Phaserange(l1psr_amb: Double): Double = getL1Pseudorange(l1psr_amb) + l1phrl1psr

	fun setL1Phaserange(l1psr_amb: Double, value: Double) {
		var diff = value - getL1Pseudorange(l1psr_amb)
		if (diff < min_l1phrl1psrDouble || diff > max_l1phrl1psrDouble) {
			diff %= (1500 * waveL1)
			if (diff < 0) diff += 1500 * waveL1
		}
		l1phrl1psr = diff
	}

	fun getL1Phase(l1psr_amb: Double) = getL1Phaserange(l1psr_amb) / waveL1
	fun setL1Phase(l1psr_amb: Double, value: Double) {
		setL1Phaserange(l1psr_amb, value * waveL1);
	}

	fun isL1valid() = l1phrl1psr_raw != 0x80000L

	fun setL1invalid() {
		l1phrl1psr_raw = 0x80000L
	}

	fun getL1MinLocktime():Int {
		return (0..5)
				.firstOrNull { l1locktime_ind < LLI_MAXES[it] }
				?.let { (l1locktime_ind shl it) - LLI_OFFSETS[it] }
				?: 937
	}
	fun setL1Locktime(locktime:Int) {
		l1locktime_ind = (0..5)
				.firstOrNull { locktime < LLT_MAXES[it] }
				?.let { (locktime + LLI_OFFSETS[it]) shr it }
				?: 127
	}

	fun getL2Pseudorange(l1psr_amb: Double) = getL1Pseudorange(l1psr_amb) + l2l1psrdiff
	fun setL2Pseudorange(l1psr_amb: Double, value: Double) {
		l2l1psrdiff = value - getL1Pseudorange(l1psr_amb)
	}

	fun getL2Phaserange(l1psr_amb: Double) = getL1Pseudorange(l1psr_amb) + l2phrl1psr
	fun setL2Phaserange(l1psr_amb: Double, value: Double) {
		var diff = value - getL1Pseudorange(l1psr_amb)
		if (diff < min_l2phrl1psrDouble || diff > max_l2phrl1psrDouble) {
			diff %= (1500 * waveL2)
			if (diff < 0) diff += 1500 * waveL2
		}
		l2phrl1psr = diff
	}


	fun getL2Phase(l1psr_amb: Double) = getL2Phaserange(l1psr_amb) / waveL2
	fun setL2Phase(l1psr_amb: Double, value: Double) {
		setL2Phaserange(l1psr_amb, value * waveL2)
	}

	fun isL2codeValid() = hasL2 && l2l1psrdiff_raw != 0x2000L

	fun isL2phaseValid() = hasL2 && l2phrl1psr_raw != 0x80000L

	fun setL2codeInvalid() {
		l2l1psrdiff_raw = 0x2000L
	}

	fun setL2phaseInvalid() {
		l2phrl1psr_raw = 0x80000L
	}
	fun getL2MinLocktime():Int {
		return (0..5)
				.firstOrNull { l2locktime_ind < LLI_MAXES[it] }
				?.let { (l2locktime_ind shl it) - LLI_OFFSETS[it] }
				?: 937
	}
	fun setL2Locktime(locktime:Int) {
		l2locktime_ind = (0..5)
				.firstOrNull { locktime < LLT_MAXES[it] }
				?.let { (locktime + LLI_OFFSETS[it]) shr it }
				?: 127
	}
}

abstract class RtcmSatCommon_1001_1004(
		override final val def: RtcmSatCommonDef_1001_1004<*>, bb: ByteBuffer, offset: Int) :
		Rtcm3v0RtkSatCommon(def.hasL2,def.hasamb,def.hascnr, LIGHTMS,def, bb, offset) {
	override val waveL1 = GnssConstants.GPS_L1_WAVELENGTH
	override val waveL2 = GnssConstants.GPS_L2_WAVELENGTH
	override val min_l1phrl1psrDouble: Double
		get() = def.l1phrl1psr_def.minDouble
	override val max_l1phrl1psrDouble: Double
		get() = def.l1phrl1psr_def.maxDouble
	override val min_l2phrl1psrDouble: Double
		get() = def.l2phrl1psr_def?.minDouble?: error("No L2")
	override val max_l2phrl1psrDouble: Double
		get() = def.l2phrl1psr_def?.maxDouble?: error("No L2")
	var sat_id: Int by def.sat_id_def
	var l1code_ind: Int by def.l1code_ind_def
	override var l1psr: Double by def.l1psr_def
	var l1psr_raw: Long by def.l1psr_def.raw
	override var l1phrl1psr: Double by def.l1phrl1psr_def
	override var l1phrl1psr_raw: Long by def.l1phrl1psr_def.raw
	override var l1locktime_ind: Int by def.l1locktime_ind_def
	override var l1psr_amb: Double by def.l1psr_amb_def ?: errDoubleAccessor
	override var l1psr_amb_raw: Long by def.l1psr_amb_def?.raw ?: errLongAccessor
	override var l1cnr: Double by def.l1cnr_def ?: errDoubleAccessor
	var l1cnr_raw: Long by def.l1cnr_def?.raw ?: errLongAccessor
	var l2code_ind: Int by def.l2code_ind_def ?: errIntAccessor
	override var l2l1psrdiff_raw: Long by def.l2l1psrdiff_def?.raw ?: errLongAccessor
	override var l2l1psrdiff: Double by def.l2l1psrdiff_def ?: errDoubleAccessor
	override var l2phrl1psr: Double by def.l2phrl1psr_def ?: errDoubleAccessor
	override var l2phrl1psr_raw: Long by def.l2phrl1psr_def?.raw ?: errLongAccessor
	override var l2locktime_ind: Int by def.l2locktime_ind_def ?: errIntAccessor
	override var l2cnr: Double by def.l2cnr_def ?: errDoubleAccessor
	var l2cnr_raw: Long by def.l2cnr_def?.raw ?: errLongAccessor

	override fun toString(): String {
		var s = String.format(Locale.ENGLISH, "%d,%d,%.2f,%.4f,%d", sat_id, l1code_ind, l1psr, l1phrl1psr, l1locktime_ind)
		if (hasamb) s+= String.format(Locale.ENGLISH, ",%.3f,%.2f", l1psr_amb, l1cnr)
		if (hasL2) {
			s += String.format(Locale.ENGLISH, ",%d,%.2f,%.4f,%d", l2code_ind, l2l1psrdiff, l2phrl1psr, l2locktime_ind)
			if (hasamb) s += String.format(Locale.ENGLISH, ",%.2f", l2cnr)
		}
		return s

	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as RtcmSatCommon_1001_1004

		if (def != other.def) return false
		if (sat_id != other.sat_id) return false
		if (l1code_ind != other.l1code_ind) return false
		if (l1psr_raw != other.l1psr_raw) return false
		if (l1phrl1psr_raw != other.l1phrl1psr_raw) return false
		if (l1locktime_ind != other.l1locktime_ind) return false
		if (def.hasamb && l1psr_amb_raw != other.l1psr_amb_raw) return false
		if (def.hascnr && l1cnr_raw != other.l1cnr_raw) return false
		if (def.hasL2 && l2code_ind != other.l2code_ind) return false
		if (def.hasL2 && l2l1psrdiff_raw != other.l2l1psrdiff_raw) return false
		if (def.hasL2 && l2phrl1psr_raw != other.l2phrl1psr_raw) return false
		if (def.hasL2 && l2locktime_ind != other.l2locktime_ind) return false
		if (def.hasL2 && def.hascnr && l2cnr_raw != other.l2cnr_raw) return false
		return true
	}

	override fun hashCode(): Int {
		var result = def.hashCode()
		result = 31 * result + waveL1.hashCode()
		result = 31 * result + def.hashCode()
		result = 31 * result + sat_id.hashCode()
		result = 31 * result + l1code_ind.hashCode()
		result = 31 * result + l1psr_raw.hashCode()
		result = 31 * result + l1phrl1psr_raw.hashCode()
		result = 31 * result + l1locktime_ind.hashCode()
		if (def.hasamb) result = 31 * result + l1psr_amb_raw.hashCode()
		if (def.hascnr) result = 31 * result + l1cnr_raw.hashCode()
		if (def.hasL2) result = 31 * result + l2code_ind.hashCode()
		if (def.hasL2) result = 31 * result + l2l1psrdiff_raw.hashCode()
		if (def.hasL2) result = 31 * result + l2phrl1psr_raw.hashCode()
		if (def.hasL2) result = 31 * result + l2locktime_ind.hashCode()
		if (def.hasL2 && def.hascnr) result = 31 * result + l2cnr_raw.hashCode()
		return result
	}
}

class Rtcm1001(bb: ByteBuffer, offset: Int = 0) : RtcmCommon_1001_1004<Rtcm1001.Sat1001>(Companion, bb, offset) {
	class Sat1001(bb: ByteBuffer, offset: Int) : RtcmSatCommon_1001_1004(Companion, bb, offset) {
		companion object : RtcmSatCommonDef_1001_1004<Sat1001>(false,false) {
			override fun binding(bb: ByteBuffer, structOffset: Int) = Sat1001(bb, structOffset)
		}
	}
	companion object : RtcmCommonDef_1001_1004<Rtcm1001,Sat1001>(Sat1001.Companion,1001) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1001(bb, structOffset)
	}
}

class Rtcm1002(bb: ByteBuffer, offset: Int = 0) : RtcmCommon_1001_1004<Rtcm1002.Sat1002>(Companion, bb, offset) {
	class Sat1002(bb: ByteBuffer, offset: Int) : RtcmSatCommon_1001_1004(Companion, bb, offset) {
		var l1Pseudorange: Double
			get() = getL1Pseudorange(l1psr_amb)
			set(value) {
				setL1Pseudorange(extractL1PsrAmb(value),value)
			}

		var l1Phaserange: Double
			get() = getL1Phaserange(l1psr_amb)
			set(value) {
				setL1Phaserange(l1psr_amb, value)
			}

		var l1Phase: Double
			get() = getL1Phase(l1psr_amb)
			set(value) {
				setL1Phase(l1psr_amb,value)
			}


		companion object : RtcmSatCommonDef_1001_1004<Sat1002>(true,false) {
			override fun binding(bb: ByteBuffer, structOffset: Int) = Sat1002(bb, structOffset)
		}
	}

	companion object : RtcmCommonDef_1001_1004<Rtcm1002,Sat1002>(Sat1002.Companion,1002) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1002(bb, structOffset)

		fun allocate(nsats:Int):Rtcm1002 {
			val bb0 = ByteBuffer.allocate(minFixedSize())
			val m0 = Rtcm1002(bb0)
			m0.num_sat = nsats
			val bb = ByteBuffer.allocate(byteSize(m0))
			return Rtcm1002(bb)
		}
	}
}

class Rtcm1003(bb: ByteBuffer, offset: Int = 0) : RtcmCommon_1001_1004<Rtcm1003.Sat1003>(Companion, bb, offset) {
	class Sat1003(bb: ByteBuffer, offset: Int) : RtcmSatCommon_1001_1004(Companion, bb, offset) {
		companion object : RtcmSatCommonDef_1001_1004<Sat1003>(false,true) {
			override fun binding(bb: ByteBuffer, structOffset: Int) = Sat1003(bb, structOffset)
		}
	}
	companion object : RtcmCommonDef_1001_1004<Rtcm1003,Sat1003>(Sat1003.Companion,1003) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1003(bb, structOffset)
	}
}


class Rtcm1004(bb: ByteBuffer, offset: Int = 0) : RtcmCommon_1001_1004<Rtcm1004.Sat1004>(Companion, bb, offset) {
	class Sat1004(bb: ByteBuffer, offset: Int) : RtcmSatCommon_1001_1004(Companion, bb, offset) {
		var l1Pseudorange: Double
			get() = getL1Pseudorange(l1psr_amb)
			set(value) {
				setL1Pseudorange(extractL1PsrAmb(value),value)
			}

		var l1Phaserange: Double
			get() = getL1Phaserange(l1psr_amb)
			set(value) {
				setL1Phaserange(l1psr_amb, value)
			}

		var l2Pseudorange: Double
			get() = getL2Pseudorange(l1psr_amb)
			set(value) {
				setL2Pseudorange(l1psr_amb,value)
			}

		var l2Phaserange: Double
			get() = getL2Phaserange(l1psr_amb)
			set(value) {
				setL2Phaserange(l1psr_amb,value)
			}

		var l1Phase: Double
			get() = getL1Phase(l1psr_amb)
			set(value) {
				setL1Phase(l1psr_amb,value)
			}

		var l2Phase: Double
			get() = getL2Phase(l1psr_amb)
			set(value) {
				setL2Phase(l1psr_amb,value)
			}

		companion object : RtcmSatCommonDef_1001_1004<Sat1004>(true, true) {
			override fun binding(bb: ByteBuffer, structOffset: Int) = Sat1004(bb, structOffset)
		}
	}

	companion object : RtcmCommonDef_1001_1004<Rtcm1004,Sat1004>(Sat1004.Companion,1004) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1004(bb, structOffset)

		fun allocate(nsats:Int):Rtcm1004 {
			val bb0 = ByteBuffer.allocate(minFixedSize())
			val m0 = Rtcm1004(bb0)
			m0.num_sat = nsats
			val bb = ByteBuffer.allocate(byteSize(m0))
			return Rtcm1004(bb)
		}
	}
}

val LLI_MAXES = listOf(24, 48, 72, 96, 120, 127)
val LLI_OFFSETS = listOf(0, 24, 120, 408, 1176, 3096)
val LLT_MAXES = listOf(24, 72, 168, 360, 744, 937)
val LLT_MAXIMUM = LLT_MAXES[LLT_MAXES.size - 1]

