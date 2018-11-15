package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.formats.rtcm3.Rtcm3MessageDef
import java.io.PrintStream
import java.nio.ByteBuffer
import java.util.*
import kotlin.math.PI

class Rtcm1019(bb: ByteBuffer, offset: Int = 0) : Rtcm3Message(Companion, bb, offset) {
    val sat_id by sat_id_def
    val week_number by week_number_def
    val sv_accuracy by sv_accuracy_def
    val code_on_l2 by code_on_l2_def
    val idot by idot_def
    val iode by iode_def
    val toc by toc_def
    val af2 by af2_def
    val af1 by af1_def
    val af0 by af0_def
    val iodc by iodc_def
    val c_rs by c_rs_def
    val delta_n by delta_n_def
    val m0 by m0_def
    val c_uc by c_uc_def
    val eccentricity_e by eccentricity_e_def
    val c_us by c_us_def
    val a12 by a_1_2_def
    val toe by toe_def
    val c_ic by c_ic_def
    val omega0 by omega0_def
    val c_is by c_is_def
    val i0 by i0_def
    val c_rc by c_rc_def
    val omega by omega_def
    val omegadot by omegadot_def
    val tgd by tgd_def
    val sv_health by sv_health_def
    val l2_p_data_flag by l2_p_data_flag_def
    val fit_interval by fit_interval_def

    override fun bodyToString(): String {
        return "$sat_id, $week_number, $sv_accuracy, $code_on_l2, $idot, $iode, $toc, $af2, $af1, $af0, $iodc, $c_rs, $delta_n, " +
                "$m0, $c_uc, $eccentricity_e, $c_us, $a12, $toe, $c_ic, $omega0, $c_is, $i0, $c_rc, $omega, $omegadot, $tgd, " +
                "$sv_health, $l2_p_data_flag, $fit_interval"
    }

    companion object : Rtcm3MessageDef<Rtcm1019>(1019) {
        override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm1019(bb, structOffset)

        val sat_id_def = DF009()
        val week_number_def = DF076()
        val sv_accuracy_def = DF077()
        val code_on_l2_def = DF078()
        val idot_def = DF079()
        val iode_def = DF071()
        val toc_def = DF081()
        val af2_def = DF082()
        val af1_def = DF083()
        val af0_def = DF084()
        val iodc_def = DF085()
        val c_rs_def = DF086()
        val delta_n_def = DF087()
        val m0_def = DF088()
        val c_uc_def = DF089()
        val eccentricity_e_def = DF090()
        val c_us_def = DF091()
        val a_1_2_def = DF092()
        val toe_def = DF093()
        val c_ic_def = DF094()
        val omega0_def = DF095()
        val c_is_def = DF096()
        val i0_def = DF097()
        val c_rc_def = DF098()
        val omega_def = DF099()
        val omegadot_def = DF100()
        val tgd_def = DF101()
        val sv_health_def = DF102()
        val l2_p_data_flag_def = DF103()
        val fit_interval_def = DF137()
    }
}

fun Rtcm1019.rinexlikeOutput(to: PrintStream) {
	val toc_s = toc % 60
	val toc_m = (toc / 60) % 60
	val toc_h = (toc / 60 / 60) % 24
	to.println("%2d %2s %2s %2s %2s %2s%5.1f%19.12e%19.12e%19.12e".format(Locale.ENGLISH,
			sat_id,
			"y", "m", "d", toc_h, toc_m, toc_s.toDouble(),
			af0, // s
			af1, // s/s
			af2  // s/s^2
	) + "    // PRN / EPOCH / SV CLK")
	to.println("   %19.12e%19.12e%19.12e%19.12e".format(Locale.ENGLISH,
			iode.toDouble(),
			c_rs, //         m
			delta_n * PI, // semicircles/s -> rad/s
			m0 * PI //       semicircles -> rad
	) + "    //  BROADCAST ORBIT - 1")
	to.println("   %19.12e%19.12e%19.12e%19.12e".format(Locale.ENGLISH,
			c_uc, //        rad
			eccentricity_e,
			c_us, //        rad
			a12 //          sqrt m
	) + "    //  BROADCAST ORBIT - 2")
	to.println("   %19.12e%19.12e%19.12e%19.12e".format(Locale.ENGLISH,
			toe.toDouble(), // s
			c_ic, //           rad
			omega0 * PI, //    semicircles -> rad
			c_is // rad
	) + "    //  BROADCAST ORBIT - 3")
	to.println("   %19.12e%19.12e%19.12e%19.12e".format(Locale.ENGLISH,
			i0 * PI, //      semicircles -> rad
			c_rc, // m
			omega * PI, //   semicircles -> rad
			omegadot * PI // semicircles/s -> rad/s
	) + "    //  BROADCAST ORBIT - 4")
	to.println("   %19.12e%19.12e%19.12e%19s".format(Locale.ENGLISH,
			idot * PI, //             semicircles/s -> rad/s
			code_on_l2.toDouble(),
			week_number.toDouble(),
			l2_p_data_flag
	) + "    //  BROADCAST ORBIT - 5")
	to.println("   %19.12e%19.12e%19.12e%19.12e".format(Locale.ENGLISH,
			sv_accuracy.toDouble(), // m
			sv_health.toDouble(),
			tgd, //                    s
			iodc.toDouble()
	) + "    //  BROADCAST ORBIT - 6")
	to.println("   %19s%19s%19s%19s".format(Locale.ENGLISH,
			"?", // s
			fit_interval,
			"",
			""
	) + "    //  BROADCAST ORBIT - 7")
}