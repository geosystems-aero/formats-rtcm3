package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import java.nio.ByteBuffer
import java.util.*

/**
 * Created by aimozg on 29.12.2016.
 * Confidential.
 */
abstract class RtcmCommonDef_1005_1006<T : StructBinding>(mid_const:Int): Rtcm3MessageDef<T>(mid_const) {
	val refstn_id_def = DF003()
	val itrf_year_def = DF021()
	val gps_ind_def = DF022()
	val glo_ind_def = DF023()
	val gal_ind_def = DF024()
	val ref_stn_ind_def = DF141()
	val ant_x_def = DF025()
	val single_osc_ind_def = DF142()
	val reserved1_def = DF001(1)
	val ant_y_def = DF026()
	val quarter_cycle_ind_def = DF364()
	val ant_z_def = DF027()
}
abstract class RtcmCommon_1005_1006(def: RtcmCommonDef_1005_1006<*>, bb: ByteBuffer, bitOffset:Int): Rtcm3Message(def,bb, bitOffset) {
	var refstn_id:Int by def.refstn_id_def
	var itrf_year:Int by def.itrf_year_def
	var gps_ind:Boolean by def.gps_ind_def
	var glo_ind:Boolean by def.glo_ind_def
	var gal_ind:Boolean by def.gal_ind_def
	var ref_stn_ind:Boolean by def.ref_stn_ind_def
	var ant_x:Double by def.ant_x_def
	var ant_x_raw:Long by def.ant_x_def.raw
	var single_osc_ind:Boolean by def.single_osc_ind_def
	var reserved1:Int by def.reserved1_def
	var ant_y:Double by def.ant_y_def
	var ant_y_raw:Long by def.ant_y_def.raw
	var quarter_cycle_ind:Int by def.quarter_cycle_ind_def
	var ant_z:Double by def.ant_z_def
	var ant_z_raw:Long by def.ant_z_def.raw
}
class Rtcm1005(bb:ByteBuffer, bitOffset: Int = 0): RtcmCommon_1005_1006(Rtcm1005.Companion,bb,bitOffset) {
	override fun bodyToString(): String {
		return String.format(Locale.ENGLISH, "%d,%d,%s,%s,%s,%s,%.4f,%s,%d,%.4f,%d,%.4f",
				refstn_id, itrf_year, gps_ind, glo_ind,
				gal_ind, ref_stn_ind, ant_x, single_osc_ind,
				reserved1, ant_y, quarter_cycle_ind,
				ant_z)
	}
	companion object : RtcmCommonDef_1005_1006<Rtcm1005>(1005){
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1005(bb, structOffset)
	}
}

class Rtcm1006(bb:ByteBuffer, bitOffset: Int = 0): RtcmCommon_1005_1006(Rtcm1006.Companion,bb,bitOffset) {
	var ant_hgt:Double by ant_hgt_def
	override fun bodyToString(): String {
		return String.format(Locale.ENGLISH, "%d,%d,%s,%s,%s,%s,%.4f,%s,%d,%.4f,%d,%.4f,%.4f",
				refstn_id, itrf_year, gps_ind, glo_ind,
				gal_ind, ref_stn_ind, ant_x, single_osc_ind,
				reserved1, ant_y, quarter_cycle_ind,
				ant_z, ant_hgt)
	}
	companion object : RtcmCommonDef_1005_1006<Rtcm1006>(1006){
		val ant_hgt_def = DF028()
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1006(bb, structOffset)

		fun allocate():Rtcm1006 = Rtcm1006(ByteBuffer.allocate(minFixedSize()))
	}
}

