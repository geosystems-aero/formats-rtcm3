package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import java.nio.ByteBuffer

/**
 * Created by aimozg on 06.02.2017.
 * Confidential.
 */
class Rtcm1033(bb:ByteBuffer, offset:Int = 0): Rtcm3Message(Companion, bb, offset) {
	var refstn_id by refstn_id_def
	var antenna_descriptor_counter by antenna_descriptor_counter_def
	var antenna_descriptor by antenna_descriptor_def
	var antenna_setup_id by antenna_setup_id_def
	var antenna_serial_number_counter by antenna_serial_number_counter_def
	var antenna_serial_number by antenna_serial_number_def
	var receiver_type_descriptor_counter by receiver_type_descriptor_counter_def
	var receiver_type_descriptor by receiver_type_descriptor_def
	var receiver_firmware_version_counter by receiver_firmware_version_counter_def
	var receiver_firmware_version by receiver_firmware_version_def
	var receiver_serial_number_counter by receiver_serial_number_counter_def
	var receiver_serial_number by receiver_serial_number_def

	override fun bodyToString(): String {
		return "$refstn_id,'$antenna_descriptor',$antenna_setup_id,'$antenna_serial_number','$receiver_type_descriptor','$receiver_firmware_version','$receiver_serial_number'"
	}

	companion object : Rtcm3MessageDef<Rtcm1033>(1033){
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1033(bb,structOffset)

		val refstn_id_def = DF003()
		val antenna_descriptor_counter_def = DF029()
		val antenna_descriptor_def = DF030(antenna_descriptor_counter_def)
		val antenna_setup_id_def = DF031()
		val antenna_serial_number_counter_def = DF032()
		val antenna_serial_number_def = DF033(antenna_serial_number_counter_def)
		val receiver_type_descriptor_counter_def = DF227()
		val receiver_type_descriptor_def = DF228(receiver_type_descriptor_counter_def)
		val receiver_firmware_version_counter_def = DF229()
		val receiver_firmware_version_def = DF230(receiver_firmware_version_counter_def)
		val receiver_serial_number_counter_def = DF231()
		val receiver_serial_number_def = DF232(receiver_serial_number_counter_def)

		fun allocate(antenna_descriptor_length:Int,
		             antenna_serial_number_length:Int,
		             receiver_type_descriptor_length:Int,
		             receiver_firmware_version_length:Int,
		             receiver_serial_number_length:Int):Rtcm1033 {
			val bb0 = ByteBuffer.allocate(minFixedSize()+
					antenna_descriptor_length+antenna_serial_number_length+receiver_type_descriptor_length+receiver_firmware_version_length+receiver_serial_number_length)
			val msg0 = Rtcm1033(bb0)
			msg0.antenna_descriptor_counter = antenna_descriptor_length
			msg0.antenna_serial_number_counter = antenna_serial_number_length
			msg0.receiver_type_descriptor_counter = receiver_type_descriptor_length
			msg0.receiver_firmware_version_counter = receiver_firmware_version_length
			msg0.receiver_serial_number_counter = receiver_serial_number_length
			if (byteSize(msg0) != bb0.limit()) return Rtcm1033(ByteBuffer.allocate(byteSize(msg0)))
			return msg0
		}
	}
}