package aero.geosystems.formats.rtcm3

import aero.geosystems.formats.*
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class Rtcm3StructDef<BINDING : StructBinding> : StructDef<BINDING>() {
	/**
	 * Reserved, bit(n)
	 */
	fun DF001(n: Int) = UIntMember(n)

	/**
	 * Message Number, 0-4095, uint12
	 */
	fun DF002() = UIntMember(12)

	/**
	 * Reference Station ID, 0-4095, uint12
	 */
	fun DF003() = UIntMember(12)

	/**
	 * GPS Epoch Time (TOW), 0-604,799,999 ms, uint30 x 1 ms
	 */
	fun DF004() = ULongMember(30)

	/**
	 * Synchronous GNSS Message Flag, bit(1)
	 */
	fun DF005() = BitMember()

	/**
	 * No. of GPS Satellite Signals Processed, 0-31, uint5
	 */
	fun DF006() = UIntMember(5)

	/**
	 * GPS Divergence-free Smoothing Indicator, bit(1)
	 */
	fun DF007() = BitMember()

	/**
	 * GPS Smoothing Interval, bit(3)
	 */
	fun DF008() = UIntMember(3)

	/**
	 * GPS Satellite ID, 1-63, uint6
	 */
	fun DF009() = UIntMember(6)

	/**
	 * GPS L1 Code Indicator, bit(1)
	 */
	fun DF010() = UIntMember(1)

	/**
	 * GPS L1 Pseudorange, 0-299,792.46 m, uint24 x 0.02 m
	 */
	fun DF011() = LinearFloatMember(true, 24, 0.02)

	/**
	 * GPS L1 PhaseRange - L1 Pseudorange, ±262.1435 m, int20 x 0.0005 m
	 */
	fun DF012() = LinearFloatMember(false, 20, 0.0005)

	/**
	 * GPS L1 Lock Time Indicator, uint7
	 */
	fun DF013() = UIntMember(7)

	/**
	 * GPS Integer L1 Pseudorange Modulus Ambiguity, 0-76,447,076.790 m, uint8 x 299,792.458 m
	 */
	fun DF014() = LinearFloatMember(true, 8, LIGHTMS)

	/**
	 * GPS L1 CNR, 0-63.75 dB-Hz, uint8 x 0.25 db-Hz
	 */
	fun DF015() = LinearFloatMember(true, 8, 0.25)

	/**
	 * GPS L2 Code Indicator, bit(2)
	 */
	fun DF016() = UIntMember(2)

	/**
	 * GPS L2-L1 Pseudorange Difference, ±163.82 m, int14 x 0.02 m
	 */
	fun DF017() = LinearFloatMember(false, 14, 0.02)

	/**
	 * GPS L2 PhaseRange - L1 Pseudorange, ±262.1435 m, int20 x 0.0005 m
	 */
	fun DF018() = LinearFloatMember(false, 20, 0.0005)

	/**
	 * GPS L2 Lock Time Indicator, uint7
	 */
	fun DF019() = UIntMember(7)

	/**
	 * GPS L2 CNR, 0-63.75 db-Hz, uint8 x 0.25 dB-Hz
	 */
	fun DF020() = LinearFloatMember(true, 8, 0.25)

	/**
	 * ITRF Realization Year, uint6
	 */
	fun DF021() = UIntMember(6)

	/**
	 * GPS Indicator, bit(1)
	 */
	fun DF022() = BitMember()

	/**
	 * GLONASS Indicator, bit(1)
	 */
	fun DF023() = BitMember()

	/**
	 * Galileo Indicator, bit(1)
	 */
	fun DF024() = BitMember()

	/**
	 * Antenna Ref. Point ECEF-X, ± 13,743,895.3471 m, 0.0001 m x int38
	 */
	fun DF025() = LinearFloatMember(false, 38, 0.0001)

	/**
	 * Antenna Ref. Point ECEF-Y, ± 13,743,895.3471 m, 0.0001 m x int38
	 */
	fun DF026() = LinearFloatMember(false, 38, 0.0001)

	/**
	 * Antenna Ref. Point ECEF-Z, ± 13,743,895.3471 m, 0.0001 m x int38
	 */
	fun DF027() = LinearFloatMember(false, 38, 0.0001)

	/**
	 * Antenna Height, 0-6.5535 m, 0.0001 m x uint16
	 */
	fun DF028() = LinearFloatMember(false, 16, 0.0001)

	/**
	 * Descriptor Counter, 0-31, uint8
	 */
	fun DF029() = UIntMember(8)

	/**
	 * Antenna Descriptor, char8 (n)
	 */
	fun DF030(df029: UIntMember) = VarStringMember(df029)

	/**
	 * Antenna Setup ID 0-255, uint8
	 */
	fun DF031() = UIntMember(8)

	/**
	 * Serial Number Counter 0-31, uint8
	 */
	fun DF032() = UIntMember(8)

	/**
	 * Antenna Serial Number, char8 (n)
	 */
	fun DF033(df032: UIntMember) = VarStringMember(df032)

	/**
	 * GLONASS Epoch Time (tk) 0-86,400,999 ms, 1 ms x uint27
	 */
	fun DF034() = UIntMember(27)

	/**
	 * No. of GLONASS Satellite Signals Processed 0-31, uint5
	 */
	fun DF035() = UIntMember(5)

	/**
	 * GLONASS Divergence-free Smoothing Indicator, bit(1)
	 */
	fun DF036() = BitMember()

	/**
	 * GLONASS Smoothing Interval See Table 3.4-4, bit(3)
	 */
	fun DF037() = UIntMember(3)

	/**
	 * GLONASS Satellite ID (Satellite Slot Number) 0-63 (See Table 3.4-3), uint6
	 */
	fun DF038() = UIntMember(6)

	/**
	 * GLONASS L1 Code Indicator, bit(1)
	 */
	fun DF039() = UIntMember(1)

	/**
	 * GLONASS Satellite Frequency Channel Number 0-20 (See Table 3.4-5), uint5
	 */
	fun DF040() = UIntMember(5)

	/**
	 * GLONASS L1 Pseudorange 0-599,584.92 m, 0.02 m x uint25
	 */
	fun DF041() = LinearFloatMember(true, 25, 0.02)

	/**
	 * GLONASS L1 PhaseRange – L1 Pseudorange ± 262.1435 m (See Data Field Note) 0.0005 m, int20
	 */
	fun DF042() = LinearFloatMember(false, 20, 0.0005)

	/**
	 * GLONASS L1 Lock Time Indicator See Table 3.4-2, uint7
	 */
	fun DF043() = UIntMember(7)

	/**
	 * GLONASS Integer L1 Pseudorange Modulus Ambiguity 0-76,147,284.332 m, 599,584.916 m x uint7
	 */
	fun DF044() = LinearFloatMember(true, 7, LIGHT2MS)

	/**
	 * GLONASS L1 CNR 0-63.75 dB-Hz 0.25 dB-Hz, uint8
	 */
	fun DF045() = LinearFloatMember(true, 8, 0.25)

	/**
	 * GLONASS L2 Code Indicator, bit(2)
	 */
	fun DF046() = UIntMember(2)

	/**
	 * GLONASS L2-L1 Pseudorange Difference ± 163.82 m (See Data Field Note) 0.02m, int14
	 */
	fun DF047() = LinearFloatMember(false, 14, 0.02)

	/**
	 * GLONASS L2 PhaseRange – L1 Pseudorange ± 262.1435 m (See Data Field Note) 0.0005 m, int20
	 */
	fun DF048() = LinearFloatMember(false, 20, 0.0005)

	/**
	 * GLONASS L2 Lock Time Indicator See Table 3.4-2, uint7
	 */
	fun DF049() = UIntMember(7)

	/**
	 * GLONASS L2 CNR 0-63.75 dB-Hz 0.25 dB-Hz, uint8
	 */
	fun DF050() = LinearFloatMember(true, 8, 0.25)

	/**
	 * Modified Julian Day (MJD) Number 0-65,535 days 1 day, uint16
	 */
	fun DF051() = UIntMember(16)

	/**
	 * Seconds of Day (UTC) 0-86,400 s 1 second, uint17
	 */
	fun DF052() = UIntMember(17)

	/**
	 * Number of Message ID Announcements to Follow (Nm) 0-31 1, uint5
	 */
	fun DF053() = UIntMember(5)

	/**
	 * Leap Seconds, GPS-UTC 0-254 s 1 second, uint8
	 */
	fun DF054() = UIntMember(8)

	/**
	 * Message ID 0-4095 1, uint12
	 */
	fun DF055() = UIntMember(12)

	/**
	 * Message Sync Flag, bit(1)
	 */
	fun DF056() = BitMember()

	/**
	 * Message Transmission Interval 0-6,553.5 s 0.1 seconds, uint16
	 */
	fun DF057() = LinearFloatMember(true, 16, 0.1)

	/**
	 * Number of Auxiliary Stations Transmitted 0 – 31 1, uint5
	 */
	fun DF058() = UIntMember(5)

	/**
	 * Network ID 0 - 255 1, uint8
	 */
	fun DF059() = UIntMember(8)

	/**
	 * Master Reference Station ID 0 – 4095 1, uint12
	 */
	fun DF060() = UIntMember(12)

	/**
	 * Auxiliary Reference Station ID 0 – 4095 1, uint12
	 */
	fun DF061() = UIntMember(12)

	/**
	 * Aux-Master Delta Latitude ±13.1071 degrees 25 x 10-6 degrees, int20
	 */
	fun DF062() = LinearFloatMember(true, 20, 1e-6)

	/**
	 * Aux-Master Delta Longitude ±26.2142 degrees 25 x 10-6 degrees, int21
	 */
	fun DF063() = LinearFloatMember(true, 21, 1e-6)

	/**
	 * Aux-Master Delta Height ±4194.303 m 1 mm, int23
	 */
	fun DF064() = LinearFloatMember(true, 23, 1e-3)

	/**
	 * GPS Epoch Time (GPS TOW) 0 - 603,799.9 sec 0.1 sec, uint23
	 */
	fun DF065() = LinearFloatMember(true, 23, 0.1)

	/**
	 * GPS Multiple Message Indicator 0-1 1, bit(1)
	 */
	fun DF066() = BitMember()

	/**
	 * Number of GPS Satellites 0 - 15 1, uint4
	 */
	fun DF067() = UIntMember(4)

	/**
	 * GPS Satellite ID 1 – 32 1, uint6
	 */
	fun DF068() = UIntMember(6)

	/**
	 * GPS Ionospheric Carrier Phase Correction Difference ±32.767 m 0.5 mm, int17
	 */
	fun DF069() = LinearFloatMember(true, 17, 0.0005)

	/**
	 * GPS Geometric Carrier Phase Correction Difference ±32.767 m 0.5 mm, int17
	 */
	fun DF070() = LinearFloatMember(true, 17, 0.0005)

	/**
	 * GPS IODE 1, bit(8)
	 */
	fun DF071() = UIntMember(8)

	/**
	 * Subnetwork ID 0 – 15, uint4
	 */
	fun DF072() = UIntMember(4)

	/**
	 * RESERVED for Provider ID 0 – 255, uint8
	 */
	fun DF073() = UIntMember(8)

	/**
	 * GPS Ambiguity Status Flag 0 – 3, bit(2)
	 */
	fun DF074() = UIntMember(2)

	/**
	 * GPS Non Sync Count 0 – 7, uint3
	 */
	fun DF075() = UIntMember(3)

	/**
	 * GPS Week number 0 -1023 1 week, uint10
	 */
	fun DF076() = UIntMember(10)

	/**
	 * GPS SV Acc. (URA) N/A, bit(4)
	 */
	fun DF077() = UIntMember(4)

	/**
	 * GPS CODE ON L2 0-3 1, bit(2)
	 */
	fun DF078() = UIntMember(2)

	/**
	 * GPS IDOT See Note 1 2^-43 semicircles/sec, int14
	 */
	fun DF079() = LinearFloatMember(false, 14, 1.0 / 1L.shl(43))

	/**
	 * GPS IODE 0-255 1, uint8
	 */
	fun DF080() = UIntMember(8)

	/**
	 * GPS toc 607,784 2^4s, uint16
	 */
	fun DF081() = LinearLongMember(true, 16, 16)

	/**
	 * GPS af2 See Note 1 2-55 sec/sec2, int8
	 */
	fun DF082() = LinearFloatMember(false, 8, 1.0 / 1L.shl(55))

	/**
	 * GPS af1 See Note 1 2-43 sec/sec, int16
	 */
	fun DF083() = LinearFloatMember(false, 16, 1.0 / 1L.shl(43))

	/**
	 * GPS af0 See Note 1 2-31 sec, int22
	 */
	fun DF084() = LinearFloatMember(false, 22, 1.0 / 1L.shl(31))

	/**
	 * GPS IODC, 0-1023, uint10
	 */
	fun DF085() = UIntMember(10)

	/**
	 * GPS C_rs, 2^-5 m x int16
	 */
	fun DF086() = LinearFloatMember(false, 16, 1.0 / 1L.shl(5))

	/**
	 * GPS DELTA n, 2^-43 semi-circles/sec x int16
	 */
	fun DF087() = LinearFloatMember(false, 16, 1.0 / 1L.shl(43))

	/**
	 * GPS M_0, 2^-31 semi-circles x int32
	 */
	fun DF088() = LinearFloatMember(false, 32, 1.0 / 1L.shl(31))

	/**
	 * GPS C_uc, 2^-29 rad x int16
	 */
	fun DF089() = LinearFloatMember(false, 16, 1.0 / 1L.shl(29))

	/**
	 * GPS Eccentricity, 2^-33 x uint32
	 */
	fun DF090() = LinearFloatMember(true, 32, 1.0 / 1L.shl(33))

	/**
	 * GPS C_us, 2^-29 rad x int16
	 */
	fun DF091() = LinearFloatMember(false, 16, 1.0 / 1L.shl(29))

	/**
	 * GPS (A)^1/2, 2^-19 m^1/2 x uint32
	 */
	fun DF092() = LinearFloatMember(true, 32, 1.0 / 1L.shl(19))

	/**
	 * GPS t_oe, 2^4 sec x uint16
	 */
	fun DF093() = LinearLongMember(true, 16, 16)

	/**
	 * GPS C_ic, 2^-29 rad x int16
	 */
	fun DF094() = LinearFloatMember(false, 16, 1.0 / 1L.shl(29))

	/**
	 * GPS OMEGA_0, 2^-31 semi-circles x int32
	 */
	fun DF095() = LinearFloatMember(false, 32, 1.0 / 1L.shl(31))

	/**
	 * GPS C_is, 2^-29 rad x int16
	 */
	fun DF096() = LinearFloatMember(false, 16, 1.0 / 1L.shl(29))

	/**
	 * GPS i_0, 2^-31 semi-circles x int32
	 */
	fun DF097() = LinearFloatMember(false, 32, 1.0 / 1L.shl(31))

	/**
	 * GPS C_rc, 2^-5 m x int16
	 */
	fun DF098() = LinearFloatMember(false, 16, 1.0 / 1L.shl(5))

	/**
	 * GPS omega (Argument of Perigee), 2^-31 semi-circles x int32
	 */
	fun DF099() = LinearFloatMember(false, 32, 1.0 / 1L.shl(31))

	/**
	 * GPS OMEGADOT (Rate of Right Ascension), 2^-43 semi-circles/sec x int24
	 */
	fun DF100() = LinearFloatMember(false, 24, 1.0 / 1L.shl(43))

	/**
	 * GPS t_GD, 2^-31 sec x int8
	 */
	fun DF101() = LinearFloatMember(false, 8, 1.0 / 1L.shl(31))

	/**
	 * GPS SV HEALTH
	 */
	fun DF102() = UIntMember(6)

	/**
	 * GPS L2 P data flag
	 */
	fun DF103() = BitMember()

	// TODO DF104-DF136

	/**
	 * GPS Fit Interval
	 */
	fun DF137() = BitMember()

	// TODO DF138-140

	/**
	 * Reference-Station Indicator, bit(1)
	 */
	fun DF141() = BitMember()

	/**
	 * Single Receiver Oscillator Indicator, bit(1)
	 */
	fun DF142() = BitMember()

	// TODO DF143-DF226

	/**
	 * Receiver Type Descriptor Counter, uint8
	 */
	fun DF227() = UIntMember(8)

	/**
	 * Receiver Type Descriptor, char8(n)
	 */
	fun DF228(df227: UIntMember) = VarStringMember(df227)

	/**
	 * Receiver Firmware Version Counter, uint8
	 */
	fun DF229() = UIntMember(8)

	/**
	 * Receiver Firmware Version, char8(n)
	 */
	fun DF230(df229: UIntMember) = VarStringMember(df229)

	/**
	 * Receiver Serial Number Counter, uint8
	 */
	fun DF231() = UIntMember(8)

	/**
	 * Receiver Serial Number, char8(n)
	 */
	fun DF232(df231: UIntMember) = VarStringMember(df231)

	// TODO DF232-DF347

	/**
	 * Galileo Epoch Time (TOW), 0-604,799,999 ms, uint30 x 1 ms
	 */
	fun DF248() = ULongMember(30)

	// TODO DF249-DF363

	/**
	 * Quarter Cucle Indicator, bit(2)
	 */
	fun DF364() = UIntMember(2)

	// TODO DF365-DF392

	/**
	 * MSM Multiple message bit, bit(1)
	 */
	fun DF393() = BitMember()

	/**
	 * GNSS Satellite mask, bit(64)
	 */
	fun DF394() = BitMaskMember(64,true)

	/**
	 * GNSS Signal mask, bit(32)
	 */
	fun DF395() = BitMaskMember(32,true)

	inner class DF396PosRef(prev: BitRef?, val df396: DF396, alignment: Int) : BitRef(prev, null, alignment) {
		override fun calcBitSize(binding: StructBinding): Int {
			return df396.gnss_sat_mask_def.getValue(binding).cardinality() * df396.gnss_sig_mask_def.getValue(binding).cardinality()
		}

	}

	/**
	 * DF396 GNSS Cell mask, bit(X)
	 */
	inner class DF396(val gnss_sat_mask_def: BitMaskReturningMember, val gnss_sig_mask_def: BitMaskReturningMember) : BitMaskReturningMember() {
		override val pos: BitRef = DF396PosRef(prev?.pos, this, mAlignment)

		override fun getValue(binding: StructBinding): BitSet {
			return readBitMask(binding.buffer, pos.start(binding), pos.calcBitSize(binding))
		}

		override fun setValue(binding: StructBinding, value: BitSet) {
			binding.clearCaches()
			writeBitMask(binding.buffer, pos.start(binding,false), pos.calcBitSize(binding), value)
		}
	}

	inner class MsmMaskFixedArrayPosRef(prev: BitRef?, val member: MsmMaskFixedArrayMember<*, *>, alignment: Int) : BitRef(prev, null, alignment) {
		override fun calcBitSize(binding: StructBinding): Int {
			return count(binding) * member.bitSize
		}

		fun count(binding: StructBinding): Int {
			return member.mask_def.getValue(binding).cardinality()
		}
	}

	abstract inner class MsmMaskFixedArrayMember<ARRAY, ARRAYITEM>(val mask_def: BitMaskReturningMember, val bitSize: Int) : Member<ARRAY>(), ReadWriteProperty<StructBinding, ARRAY>, MemberWithToRawString {
		override val pos = MsmMaskFixedArrayPosRef(prev?.pos, this, mAlignment)
		fun count(binding: StructBinding): Int =
				pos.count(binding)

		override fun setValue(thisRef: StructBinding, property: KProperty<*>, value: ARRAY) {
			setValue(thisRef, value)
		}

		abstract fun setValue(binding: StructBinding, value: ARRAY)

		protected fun getULong(index: Int, binding: StructBinding): Long =
				readBits(binding.buffer, pos.start(binding) + bitSize * index, bitSize)

		protected fun getSLong(index: Int, binding: StructBinding): Long =
				toSigned(getULong(index, binding), bitSize)

		protected fun setLong(index: Int, binding: StructBinding, value: Long) {
			writeBits(binding.buffer, value, pos.start(binding) + bitSize * index, bitSize)
		}

		abstract fun getItem(index: Int, binding: StructBinding): ARRAYITEM
		abstract fun setItem(index: Int, binding: StructBinding, value: ARRAYITEM)

		override fun toRawString(binding: StructBinding): String {
			return (0 until count(binding)).joinToString(prefix="[",postfix = "]") {
				getULong(it, binding).toString()
			}
		}
	}

	inner class MsmMaskUIntArray(mask_def: BitMaskReturningMember, bitSize: Int) : MsmMaskFixedArrayMember<IntArray, Int>(mask_def, bitSize) {
		override fun getValue(binding: StructBinding): IntArray {
			return IntArray(count(binding)) { getULong(it, binding).toInt() }
		}

		override fun setValue(binding: StructBinding, value: IntArray) {
			for (i in value.indices) setLong(i, binding, value[i].toLong())
		}

		override fun getItem(index: Int, binding: StructBinding): Int {
			return getULong(index, binding).toInt()
		}

		override fun setItem(index: Int, binding: StructBinding, value: Int) {
			setLong(index, binding, value.toLong())
		}
	}

	inner class MsmMaskIntArray(mask_def: BitMaskReturningMember, bitSize: Int) : MsmMaskFixedArrayMember<IntArray, Int>(mask_def, bitSize) {
		override fun getValue(binding: StructBinding): IntArray {
			return IntArray(count(binding)) { getSLong(it, binding).toInt() }
		}

		override fun setValue(binding: StructBinding, value: IntArray) {
			for (i in value.indices) setLong(i, binding, value[i].toLong())
		}

		override fun getItem(index: Int, binding: StructBinding): Int {
			return getSLong(index, binding).toInt()
		}

		override fun setItem(index: Int, binding: StructBinding, value: Int) {
			setLong(index, binding, value.toLong())
		}
	}

	inner class MsmMaskLinearFloatArray(
			mask_def: BitMaskReturningMember, val unsigned: Boolean, bitSize: Int, val scale: Double = 1.0, val shift: Double = 0.0
	) : MsmMaskFixedArrayMember<DoubleArray, Double>(mask_def, bitSize) {
		override fun getValue(binding: StructBinding): DoubleArray {
			return DoubleArray(count(binding)) { getItem(it, binding) }
		}

		override fun setValue(binding: StructBinding, value: DoubleArray) {
			for (i in value.indices) setItem(i, binding, value[i])
		}

		override fun getItem(index: Int, binding: StructBinding): Double {
			return lfget(scale, shift, if (unsigned) getULong(index, binding) else getSLong(index, binding))
		}

		override fun setItem(index: Int, binding: StructBinding, value: Double) {
			setLong(index, binding, lfset(scale, shift, value))
		}
	}

	inner class MsmMaskBoolArray(mask_def: BitMaskReturningMember) : MsmMaskFixedArrayMember<BooleanArray, Boolean>(mask_def, 1) {
		override fun getValue(binding: StructBinding): BooleanArray {
			return BooleanArray(count(binding)) { getItem(it, binding) }
		}

		override fun setValue(binding: StructBinding, value: BooleanArray) {
			for (i in value.indices) setItem(i, binding, value[i])
		}

		override fun getItem(index: Int, binding: StructBinding): Boolean {
			return getULong(index, binding) != 0L
		}

		override fun setItem(index: Int, binding: StructBinding, value: Boolean) {
			setLong(index, binding, if (value) 1L else 0L)
		}
	}

	/**
	 * The number of integer milliseconds in GNSS Satellite rought range, 0 - 254 ms, 1 ms x uint8
	 */
	fun DF397() = UIntMember(8)

	fun DF397array(gnss_sat_mask_def: BitMaskReturningMember) = MsmMaskUIntArray(gnss_sat_mask_def, 8)

	/**
	 * GNSS Satellite rough range modulo 1 millisecond, 0 - (1-2^-10) ms, 2^-10 ms x uint10
	 */
	fun DF398() = LinearFloatMember(true, 10, 1.0 / 1.shl(10))

	fun DF398array(gnss_sat_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_sat_mask_def, true, 10, 1.0 / 1.shl(10))

	/**
	 * GNSS Satellite rough Phase Range Rate, ±8191 m/s, 1 m/s x int14
	 */
	fun DF399() = IntMember(14)

	fun DF399array(gnss_sat_mask_def: BitMaskReturningMember) = MsmMaskIntArray(gnss_sat_mask_def, 14)

	/**
	 * GNSS signal fine Pseudorange, ±(2^-10 - 2^-24) ms, 2^-24 ms x int15
	 */
	fun DF400() = LinearFloatMember(false, 15, 1.0 / 1.shl(24))

	fun DF400array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, false, 15, 1.0 / 1.shl(24))

	/**
	 * GNSS signal fine PhaseRange data, ±(2^-8 - 2^-29) ms, 2^-29 ms x int22
	 */
	fun DF401() = LinearFloatMember(false, 22, 1.0 / 1.shl(29))

	fun DF401array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, false, 22, 1.0 / 1.shl(29))

	/**
	 * GNSS PhaseRange Lock Time Indicator
	 */
	fun DF402() = UIntMember(4)

	fun DF402array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskUIntArray(gnss_cell_mask_def, 4)

	/**
	 * GNSS signal CNR, 1-63 dbHz, uint6 x 1 dB-Hz
	 */
	fun DF403() = LinearFloatMember(true, 6)

	fun DF403array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, true, 6)

	/**
	 * GNSS signal fine Phase Range Rate, ±1.6384 m/s, 0.0001 m/s x int15
	 */
	fun DF404() = LinearFloatMember(false, 15, 1e-4)

	fun DF404array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, false, 15, 1e-4)

	/**
	 * GNSS signal fine Pseudorange with extended resolution, ±(2^10 - 2^-29) ms, int20 x 2^-29 ms
	 */
	fun DF405() = LinearFloatMember(false, 20, 1.0 / 1.shl(29))

	fun DF405array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, false, 20, 1.0 / 1.shl(29))

	/**
	 * GNSS signal fine PhaseRange data with extended resolution, ±(2^-8 - 2^-31) msg, int24 x 2^-31 ms
	 */
	fun DF406() = LinearFloatMember(false, 24, 1.0 / 1.shl(31))

	fun DF406array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, false, 24, 1.0 / 1.shl(31))

	/**
	 * GNSS PhaseRange Lock Time Indicator with extended range and resolution
	 */
	fun DF407() = UIntMember(10)

	fun DF407array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskUIntArray(gnss_cell_mask_def, 10)

	/**
	 * GNSS signal CNR with extended resolution, 0.0625-63.9375 dBHz, uint10 x 2^-4 dB-Hz
	 */
	fun DF408() = LinearFloatMember(true, 10, 0.0625)

	fun DF408array(gnss_cell_mask_def: BitMaskReturningMember) = MsmMaskLinearFloatArray(gnss_cell_mask_def, true, 10, 0.0625)

	/**
	 * IODS - Issue Of Data Station, 0-7, uint3
	 */
	fun DF409() = UIntMember(3)

	// DF410 - Reserved

	/**
	 * Clock Steering Indicator, uint2
	 */
	fun DF411() = UIntMember(2)

	/**
	 * Externdal Clock Indicator, uint2
	 */
	fun DF412() = UIntMember(2)

	/**
	 * IOD SSR, 0-15, uint4
	 */
	fun DF413() = UIntMember(4)

	/**
	 * SSR Provider ID, 0 - 65535, uint16
	 */
	fun DF414() = UIntMember(16)

	/**
	 * SSR Solution ID, 0 - 15, uint4
	 */
	fun DF415() = UIntMember(4)

	/**
	 * GLONASS Day Of Week, 0(Sunday)..6(Saturday),7(unknown), uint3
	 */
	fun DF416() = UIntMember(3)

	/**
	 * GNSS Smoothing Type Indicator, bit1
	 */
	fun DF417() = BitMember()

	/**
	 * GNSS Smoothing Interval, bit(3)
	 */
	fun DF418() = UIntMember(3)

	/**
	 * GLONASS Satellite Frequency Channel Number
	 */
	fun DF419() = UIntMember(4)

	/**
	 * Half-cycle ambiguity indicator
	 */
	fun DF420() = BitMember()

	fun DF420array(gnss_sat_mask_def: BitMaskReturningMember) = MsmMaskBoolArray(gnss_sat_mask_def)

	/**
	 * GLONASS Code-Phase Bias Indicator
	 *
	 * 0 - GLONASS code and phase measurements are not aligned to same epoch
	 * 1 - aligned
	 */
	fun DF421() = BitMember()

	/**
	 * GLONASS FDMA Signals Mask
	 *
	 * bit 0: DF423 present
	 * bit 1: DF424 present
	 * bit 2: DF425 present
	 * bit 3: DF426 present
	 */
	fun DF422() = BitMaskMember(4, true)

	inner class ConditionalLinearFloatPosRef(prev: BitRef?,
	                                         val member: ConditionalLinearFloatMember,
	                                         alignment:Int): BitRef(prev,null,alignment) {
		fun present(binding: StructBinding):Boolean = member.srcMask.getValue(binding)[member.srcBit]
		override fun calcBitSize(binding: StructBinding) = if (present(binding)) member.bitSize else 0
	}
	inner class ConditionalLinearFloatMember(
			val srcMask: BitMaskMember,
			val srcBit: Int,
			val unsigned: Boolean, val bitSize: Int, val scale: Double = 1.0, val shift: Double = 0.0) :
			Member<Double?>(), ReadWriteProperty<StructBinding, Double?> {

		override val pos: ConditionalLinearFloatPosRef = ConditionalLinearFloatPosRef(prev?.pos,this,mAlignment)

		override fun getValue(binding: StructBinding): Double? {
			if (!pos.present(binding)) return null
			var raw = readBits(binding.buffer, pos.start(binding), bitSize)
			if (!unsigned) raw = toSigned(raw,bitSize)
			return lfget(scale, shift, raw)
		}

		override operator fun setValue(thisRef: StructBinding, property: KProperty<*>, value: Double?) {
			if (pos.present(thisRef) && value != null) writeBits(thisRef.buffer, lfset(scale, shift, value), pos.start(thisRef), bitSize)
		}
	}

	/**
	 * GLONASS L1 C/A Code-Phase Bias
	 *
	 * Offset between L1 C/A Pseudorange and L1 C/A PhaseRange in meters.
	 * If DF421=0: Aligned L1 PhaseRange   = Full L1 PhaseRange + GLONASS L1 C/A Code-Phase Bias
	 * If DF421=1: Unaligned L1 PhaseRange = Full L1 PhaseRange - GLONASS L1 C/A Code-Phase Bias
	 * 0x8000: Invalid value (unknown or outside of range)
	 */
	fun DF423(df422: BitMaskMember) = ConditionalLinearFloatMember(df422, 0, false, 16, 0.02)

	/**
	 * GLONASS L1 P Code-Phase Bias
	 */
	fun DF424(df422: BitMaskMember) = ConditionalLinearFloatMember(df422, 1, false, 16, 0.02)

	/**
	 * GLONASS L2 C/A Code-Phase Bias
	 */
	fun DF425(df422: BitMaskMember) = ConditionalLinearFloatMember(df422, 2, false, 16, 0.02)

	/**
	 * GLONASS L2 P Code-Phase Bias
	 */
	fun DF426(df422: BitMaskMember) = ConditionalLinearFloatMember(df422, 3, false, 16, 0.02)

	/**
	 * BDS Epoch Time (TOW), 0-604,799,999 ms, uint30 x 1 ms
	 */
	fun DF427() = ULongMember(30)

	// TODO DF428+

	companion object {
		const val LIGHTMS = 299792458.0 / 1000.0
		const val LIGHT2MS = 2 * LIGHTMS

		data class DFLocktimeIndicator(val minRaw: Int, val maxRaw: Int,
		                               val multiplier: Int, val shift: Int,
		                               val minTime: Int) {
			constructor(raw: Int, minTime: Int) : this(raw, raw, minTime, 0, minTime)
			constructor(minRaw: Int, maxRaw: Int, multiplier: Int, shift: Int) : this(
					minRaw, maxRaw,
					multiplier, shift,
					minRaw * multiplier + shift)
			fun toMinTime(i:Int) = i*multiplier+shift
			fun toIndicator(time:Int) = if (multiplier==0)minRaw else (time-shift)/multiplier
		}

		val DF402_INDICATORS = arrayOf(
				DFLocktimeIndicator(0, 0),
				DFLocktimeIndicator(1, 32),
				DFLocktimeIndicator(2, 64),
				DFLocktimeIndicator(3, 128),
				DFLocktimeIndicator(4, 256),
				DFLocktimeIndicator(5, 512),
				DFLocktimeIndicator(6, 1024),
				DFLocktimeIndicator(7, 2048),
				DFLocktimeIndicator(8, 4096),
				DFLocktimeIndicator(9, 8192),
				DFLocktimeIndicator(10, 16384),
				DFLocktimeIndicator(11, 32768),
				DFLocktimeIndicator(12, 65536),
				DFLocktimeIndicator(13, 131072),
				DFLocktimeIndicator(14, 262144),
				DFLocktimeIndicator(15, 524288)
		)
		val DF407_INDICATORS = arrayOf(
				DFLocktimeIndicator(0, 63, 1, 0),
				DFLocktimeIndicator(64, 95, 2, -64),
				DFLocktimeIndicator(96, 127, 4, -256),
				DFLocktimeIndicator(128, 159, 8, -768),
				DFLocktimeIndicator(160, 191, 16, -2048),
				DFLocktimeIndicator(192, 223, 32, -5120),
				DFLocktimeIndicator(224, 255, 64, -12288),
				DFLocktimeIndicator(256, 287, 128, -28672),
				DFLocktimeIndicator(288, 319, 256, -65536),
				DFLocktimeIndicator(320, 351, 512, -147456),
				DFLocktimeIndicator(352, 383, 1024, -327680),
				DFLocktimeIndicator(384, 415, 2048, -720896),
				DFLocktimeIndicator(416, 447, 4096, -1572864),
				DFLocktimeIndicator(448, 479, 8192, -3407872),
				DFLocktimeIndicator(480, 511, 16384, -7340032),
				DFLocktimeIndicator(512, 543, 32768, -15728640),
				DFLocktimeIndicator(544, 575, 65536, -33554432),
				DFLocktimeIndicator(576, 607, 131072, -71303168),
				DFLocktimeIndicator(608, 639, 262144, -150994944),
				DFLocktimeIndicator(640, 671, 524288, -318767104),
				DFLocktimeIndicator(672, 703, 1048576, -671088640),
				DFLocktimeIndicator(704, 67108864)
		)
	}
}
