package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import java.nio.ByteBuffer
import java.util.*

abstract class RtcmCommonDef_1007_1008<T: StructBinding>(mid_const:Int): Rtcm3MessageDef<T>(mid_const){
	val refstn_id_def = DF003()
	val descriptor_counter_def = DF029()
	val antenna_descriptor_def = DF030(descriptor_counter_def)
	val antenna_setup_id_def = DF031()
}

abstract class RtcmCommon_1007_1008(def: RtcmCommonDef_1007_1008<*>, bb: ByteBuffer, bitOffset:Int): Rtcm3Message(def,bb,bitOffset) {
	var refstn_id:Int by def.refstn_id_def
	var descriptor_counter:Int by def.descriptor_counter_def
	var antenna_descriptor:String by def.antenna_descriptor_def
	var antenna_setup_id:Int by def.antenna_setup_id_def
}

class Rtcm1007(bb: ByteBuffer, bitOffset:Int): RtcmCommon_1007_1008(Companion,bb,bitOffset) {
	override fun bodyToString(): String {
		return String.format(Locale.ENGLISH,"%d,%d,\"%s\",%d", refstn_id,
				descriptor_counter, antenna_descriptor,
				antenna_setup_id)
	}

	companion object : RtcmCommonDef_1007_1008<Rtcm1007>(1007){
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1007(bb, structOffset)
	}
}

class Rtcm1008(bb: ByteBuffer, bitOffset:Int=0): RtcmCommon_1007_1008(Companion,bb,bitOffset) {
	var serial_number_counter:Int by serial_number_counter_def
	var serial_number:String by serial_number_def

	override fun bodyToString(): String {
		return String.format(Locale.ENGLISH,"%d,%d,\"%s\",%d,\"%s\"", refstn_id,
				descriptor_counter, antenna_descriptor,
				antenna_setup_id, serial_number)
	}

	companion object : RtcmCommonDef_1007_1008<Rtcm1008>(1008){
		val serial_number_counter_def = DF032()
		val serial_number_def = DF033(serial_number_counter_def)
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1008(bb, structOffset)

		fun allocate(antenna_descriptor_length:Int,antenna_serial_number_length:Int):Rtcm1008 {
			val bb0 = ByteBuffer.allocate(minFixedSize()+antenna_descriptor_length+antenna_serial_number_length)
			val msg0 = Rtcm1008(bb0)
			msg0.descriptor_counter = antenna_descriptor_length
			msg0.serial_number_counter = antenna_serial_number_length
			if (byteSize(msg0) != bb0.limit()) return Rtcm1008(ByteBuffer.allocate(byteSize(msg0)))
			return msg0
		}
	}
}