package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formatAs
import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import java.nio.ByteBuffer

/*
 * Created by aimozg on 18.10.2017.
 * Confidential unless published on GitHub
 */
class Rtcm1230(bb: ByteBuffer, offset:Int = 0): Rtcm3Message(Companion, bb, offset) {
	val refstn_id by refstn_id_def
	val glo_cpb_indicator by glo_cpb_indicator_def
	val glo_fdma_signals_mask_raw by glo_fdma_signals_mask_def.asLong
	val glo_fdma_signals_mask by glo_fdma_signals_mask_def
	val glo_l1ca_cpb by glo_l1ca_cpb_def
	val glo_l1p_cpb by glo_l1p_cpb_def
	val glo_l2ca_cpb by glo_l2ca_cpb_def
	val glo_l2p_cpb by glo_l2p_cpb_def

	override fun bodyToString(): String {
		return "$refstn_id,$glo_cpb_indicator,$glo_fdma_signals_mask_raw"+
				(glo_l1ca_cpb?.formatAs(",%.2f")?:"")+
				(glo_l1p_cpb?.formatAs(",%.2f")?:"")+
				(glo_l2ca_cpb?.formatAs(",%.2f")?:"")+
				(glo_l2p_cpb?.formatAs(",%.2f")?:"")
	}

	companion object: Rtcm3MessageDef<Rtcm1230>(1230) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1230(bb,structOffset)

		val refstn_id_def = DF003()
		val glo_cpb_indicator_def = DF421()
		val reserved1_def = DF001(3)
		val glo_fdma_signals_mask_def = DF422()
		val glo_l1ca_cpb_def = DF423(glo_fdma_signals_mask_def)
		val glo_l1p_cpb_def = DF424(glo_fdma_signals_mask_def)
		val glo_l2ca_cpb_def = DF425(glo_fdma_signals_mask_def)
		val glo_l2p_cpb_def = DF426(glo_fdma_signals_mask_def)
	}
}