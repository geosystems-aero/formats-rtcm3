package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.StructBinding
import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import java.nio.ByteBuffer

/*
 * Created by aimozg on 18.02.2019.
 * Confidential unless published on GitHub
 */
abstract class RtcmCommonDef_1021_1022<T : StructBinding>(mid_const: Int) : Rtcm3MessageDef<T>(mid_const) {
	val source_name_counter_def = DF143()
	val source_name_def = DF144(source_name_counter_def)
	val target_name_counter_def = DF145()
	val target_name_def = DF146(target_name_counter_def)
	val system_id_def = DF147()
	val utilized_tf_message_def = DF148()
	val plate_def = DF149()
	val comp_ind_def = DF150()
	val height_ind_def = DF151()
	val aov_origin_lat_def = DF152()
	val aov_origin_lon_def = DF153()
	val aov_ns_ext_def = DF154()
	val aov_ew_ext_def = DF155()
	val dx_def = DF156()
	val dy_def = DF157()
	val dz_def = DF158()
	val r1_def = DF159()
	val r2_def = DF160()
	val r3_def = DF161()
	val ds_def = DF162()
	val xp_def = if (mid_const == 1022) DF163() else null
	val yp_def = if (mid_const == 1023) DF164() else null
	val zp_def = if (mid_const == 1024) DF165() else null
	val add_a_s_def = DF166()
	val add_b_s_def = DF167()
	val add_a_t_def = DF168()
	val add_b_t_def = DF169()
	val horiz_quality_ind_def = DF214()
	val vert_quality_ind_def = DF215()
}

abstract class RtcmCommon_1021_1022(def: RtcmCommonDef_1021_1022<*>, bb: ByteBuffer, bitOffset: Int) : Rtcm3Message(def, bb, bitOffset) {
	var source_name_counter by def.source_name_counter_def
	var source_name by def.source_name_def
	var target_name_counter by def.target_name_counter_def
	var target_name by def.target_name_def
	var system_id by def.system_id_def
	var utilized_tf_message by def.utilized_tf_message_def
	var plate by def.plate_def
	var comp_ind by def.comp_ind_def
	var height_ind by def.height_ind_def
	var aov_origin_lat by def.aov_origin_lat_def
	var aov_origin_lon by def.aov_origin_lon_def
	var aov_ns_ext by def.aov_ns_ext_def
	var aov_ew_ext by def.aov_ew_ext_def
	var dx by def.dx_def
	var dy by def.dy_def
	var dz by def.dz_def
	var r1 by def.r1_def
	var r2 by def.r2_def
	var r3 by def.r3_def
	var ds by def.ds_def
	var add_a_s by def.add_a_s_def
	var add_b_s by def.add_b_s_def
	var add_a_t by def.add_a_t_def
	var add_b_t by def.add_b_t_def
	var horiz_quality_ind by def.horiz_quality_ind_def
	var vert_quality_ind by def.vert_quality_ind_def

	var aov_origin_lat_deg: Double
		get() = aov_origin_lat / 3600
		set(value) {
			aov_origin_lat = value * 3600
		}
	var aov_origin_lon_deg: Double
		get() = aov_origin_lon / 3600
		set(value) {
			aov_origin_lon = value * 3600
		}
	var aov_ns_ext_deg: Double
		get() = aov_ns_ext / 3600
		set(value) {
			aov_ns_ext = value * 3600
		}
	var aov_ew_ext_deg: Double
		get() = aov_ew_ext / 3600
		set(value) {
			aov_ew_ext = value * 3600
		}
	var a_s: Double
		get() = 6370000 + add_a_s
		set(value) {
			add_a_s = value - 6370000
		}
	var b_s: Double
		get() = 6350000 + add_b_s
		set(value) {
			add_b_s = value - 6350000
		}
	var a_t: Double
		get() = 6370000 + add_a_t
		set(value) {
			add_a_t = value - 6370000
		}
	var b_t: Double
		get() = 6350000 + add_b_t
		set(value) {
			add_b_t = value - 6350000
		}

	protected open fun insertBodyToString(): String = ""
	override fun bodyToString(): String {
		return "'$source_name','$target_name',$system_id,$utilized_tf_message,$plate_name,$comp_ind,$height_ind,$aov_origin_lat_deg^,$aov_origin_lon_deg^,$aov_ns_ext_deg^,$aov_ew_ext_deg^,$dx,$dy,$dz,$r1,$r2,$r3,$ds,${insertBodyToString()}$a_s,$b_s,$a_t,$b_t,$horiz_quality_ind,$vert_quality_ind"
	}
}

val RtcmCommon_1021_1022.plate_name
	get() = when (plate) {
		0 -> "unknown"
		1 -> "AFRC"
		2 -> "ANTA"
		3 -> "ARAB"
		4 -> "AUST"
		5 -> "CARB"
		6 -> "COCO"
		7 -> "EURA"
		8 -> "INDI"
		9 -> "NOAM"
		10 -> "NAZC"
		11 -> "PCFC"
		12 -> "SOAM"
		13 -> "JUFU"
		14 -> "PHIL"
		15 -> "RIVR"
		16 -> "SCOT"
		else -> plate.toString()
	}

class Rtcm1021(bb: ByteBuffer, bitOffset: Int = 0) : RtcmCommon_1021_1022(Companion, bb, bitOffset) {
	companion object : RtcmCommonDef_1021_1022<Rtcm1021>(1021) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1021(bb, structOffset)
	}
}

class Rtcm1022(bb: ByteBuffer, bitOffset: Int = 0) : RtcmCommon_1021_1022(Companion, bb, bitOffset) {
	var xp by xp_def ?: errDoubleAccessor
	var yp by yp_def ?: errDoubleAccessor
	var zp by zp_def ?: errDoubleAccessor
	override fun insertBodyToString(): String {
		return "$xp,$yp,$zp,"
	}

	companion object : RtcmCommonDef_1021_1022<Rtcm1022>(1022) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1022(bb, structOffset)
	}
}

class Rtcm1025(bb: ByteBuffer, bitOffset: Int = 0) : Rtcm3Message(Companion, bb, bitOffset) {
	var system_id by system_id_def
	var projection_type by projection_type_def
	var lano by lano_def
	var lono by lono_def
	var add_sno by add_sno_def
	var fe by fe_def
	var fn by fn_def

	var sno: Double
		get() = 1 + add_sno * 1e-6
		set(value) {
			add_sno = (value - 1) * 1e6
		}

	override fun bodyToString(): String {
		return "$system_id,$projection_type_name,$lano,$lono,$add_sno,$fe,$fn"
	}

	companion object : Rtcm3MessageDef<Rtcm1025>(1025) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1025(bb, structOffset)

		val system_id_def = DF147()
		val projection_type_def = DF170()
		val lano_def = DF171()
		val lono_def = DF172()
		val add_sno_def = DF173()
		val fe_def = DF174()
		val fn_def = DF175()
	}
}

val Rtcm1025.projection_type_name
	get() = when (projection_type) {
		0 -> "unknown"
		1 -> "TM"
		2 -> "TMS"
		3 -> "LLC1SP"
		4 -> "LLC2SP"
		5 -> "LCCW"
		6 -> "CS"
		7 -> "OM"
		8 -> "OS"
		9 -> "MC"
		10 -> "PS"
		11 -> "DS"
		else -> projection_type.toString()
	}