package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.gnss.RawSignal
import aero.geosystems.gnss.SatSystem

enum class MsmSignalCode(val code: Int, val signal: RawSignal?, val df016: Int?) {
	GPS_L1C(2, RawSignal.GPS_L1_CA, null),
	GPS_L1P(3, RawSignal.GPS_L1_PY, null),
	GPS_L1W(4, null/*RawSignal.GPS_L1_W*/, null),
	GPS_L2C(8, RawSignal.GPS_L2_C, 0),
	GPS_L2P(9, RawSignal.GPS_L2_PY_DIRECT, 1),
	GPS_L2W(10, RawSignal.GPS_L2_PY_CROSS, 2),
	GPS_L2S(15, RawSignal.GPS_L2C_M, 0),
	GPS_L2L(16, RawSignal.GPS_L2C_L, 0),
	GPS_L2X(17, RawSignal.GPS_L2C_ML, 0),
	GPS_L5I(22, /*RawSignal.GPS_L5_I*/null, null),
	GPS_L5Q(23, RawSignal.GPS_L5_Q, null),
	GPS_L5X(24, RawSignal.GPS_L5_IQ, null),
	GPS_L1_C_D(30, /*RawSignal.GPS_L1_C_D*/null, null),
	GPS_L1_C_P(31, /*RawSignal.GPS_L1_C_P*/null, null),
	GPS_L1_C_DP(32, /*RawSignal.GPS_L1_C_DP*/null, null),

	GLO_L1C(2, RawSignal.GLO_L1_CA, null),
	GLO_L1P(3, RawSignal.GLO_L1_P, null),
	GLO_L2C(8, RawSignal.GLO_L2_CA, null),
	GLO_L2P(9, RawSignal.GLO_L2_P, null),

	GAL_E1C(2, RawSignal.GAL_E1C, null),
	GAL_E1A(3, RawSignal.GAL_E1A, null),
	GAL_E1B(4, RawSignal.GAL_E1B, null),
	GAL_E1BC(5, RawSignal.GAL_E1BC, null),
	GAL_E1ABC(6, RawSignal.GAL_E1ABC, null),
	GAL_E6C(8, null/*RawSignal.GAL_E6C*/, null),
	GAL_E6A(9, null/*RawSignal.GAL_E6A*/, null),
	GAL_E6B(10, null/*RawSignal.GAL_E6B*/, null),
	GAL_E6BC(11, null/*RawSignal.GAL_E6BC*/, null),
	GAL_E6ABC(12, null/*RawSignal.GAL_E6ABC*/, null),
	GAL_E5BI(14, RawSignal.GAL_E5b_I, null),
	GAL_E5BQ(15, RawSignal.GAL_E5b_Q, null),
	GAL_E5BIQ(16, RawSignal.GAL_E5b_IQ, null),
	GAL_E5ABI(18, null/*RawSignal.GAL_E5ABI*/, null),
	GAL_E5ABQ(19, null/*RawSignal.GAL_E5ABQ*/, null),
	GAL_E5ABIQ(20, null/*RawSignal.GAL_E5ABIQ*/, null),
	GAL_E5AI(22, RawSignal.GAL_E5a_IQ, null),
	GAL_E5AQ(23, RawSignal.GAL_E5a_Q, null),
	GAL_E5AIQ(24, null/*RawSignal.GAL_E5AIQ*/, null),

	QZS_L1C(2,RawSignal.QZSS_L1CA,null),
	QZS_LEXS(9,null/*RawSignal.QZSS_LEXS*/,null),
	QZS_LEXL(10,null/*RawSignal.QZSS_LEXL*/,null),
	QZS_LEXSL(11,null/*RawSignal.QZSS_LEXSL*/,null),
	QZS_L2CM(15,RawSignal.QZSS_L2C_M,null),
	QZS_L2CL(16,RawSignal.QZSS_L2C_L,null),
	QZS_L2CML(17,RawSignal.QZSS_L2C_ML,null),
	QZS_L5I(22,null/*RawSignal.QZSS_L5I*/,null),
	QZS_L5Q(23,RawSignal.QZSS_L5Q,null),
	QZS_L5IQ(24,RawSignal.QZSS_L5_IQ,null),
	QZS_L1CD(30,RawSignal.QZSS_L1C_DP,null),
	QZS_L1CP(31,null/*RawSignal.QZSS_L1CP*/,null),
	QZS_L1CDP(32,null/*RawSignal.QZSS_L1CDP*/,null),

	BDS_1I(2,RawSignal.BDS_B1D1,null),
	BDS_1Q(3,null/*RawSignal.BDS_B1Q,*/,null),
	BDS_1IQ(4,null/*RawSignal.BDS_B1IQ,*/,null),
	BDS_3I(8,RawSignal.BDS_B3D1,null),
	BDS_3Q(9,null/*RawSignal.BDS_B3Q,*/,null),
	BDS_3IQ(10,null/*RawSignal.BDS_B3IQ,*/,null),
	BDS_2I(14,RawSignal.BDS_B2D1,null),
	BDS_2Q(15,null/*RawSignal.BDS_B2Q*/,null),
	BDS_2IQ(16,null/*RawSignal.BDS_B2IQ*/,null);
	companion object {
		fun byCode(gnss: SatSystem,code: Int) = values().find { it.code==code && gnss == it.signal?.gnss }
		fun bySignal(signal: RawSignal) =
				values().find { it.signal == signal } ?: when (signal) {
					RawSignal.GPS_L2_PY_CORRELATED -> GPS_L2W
					RawSignal.BDS_B1D2 -> BDS_1I
					RawSignal.BDS_B2D2 -> BDS_2I
					RawSignal.BDS_B3D2 -> BDS_3I
					else -> null
				}

	}
}
