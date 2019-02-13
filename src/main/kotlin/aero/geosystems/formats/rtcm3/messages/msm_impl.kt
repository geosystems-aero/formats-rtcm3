package aero.geosystems.formats.rtcm3.messages

import aero.geosystems.gnss.GnssConstants
import aero.geosystems.gnss.SatSystem

enum class MsmSignalCode(val gnss:SatSystem, val code: Int, val rinexBand:Int?,val rinexCode: Char?) {
	GPS_L1C(SatSystem.GPS, 2, 1,'C'), // L1 C/A
	GPS_L1P(SatSystem.GPS, 3, 1,'P'), // L1 P
	GPS_L1W(SatSystem.GPS, 4, 1,'W'), // L1 Z-tracking or similar
	GPS_L2C(SatSystem.GPS, 8, 2,'C'), // L2 C/A
	GPS_L2P(SatSystem.GPS, 9, 2,'P'), // L2 P
	GPS_L2W(SatSystem.GPS, 10, 2,'W'), // L2 Z-tracking or similar
	GPS_L2S(SatSystem.GPS, 15, 2,'S'), // L2 L2C(M)
	GPS_L2L(SatSystem.GPS, 16, 2,'L'), // L2 L2C(L)
	GPS_L2X(SatSystem.GPS, 17, 2,'X'), // L2 L2C(M+L)
	GPS_L5I(SatSystem.GPS, 22, 5,'I'), // L5 I
	GPS_L5Q(SatSystem.GPS, 23, 5, 'Q'), // L5 Q
	GPS_L5X(SatSystem.GPS, 24, 5, 'X'), // L5 I+Q
	GPS_L1_C_D(SatSystem.GPS, 30, 1, 'S'), // L1 L1C-D
	GPS_L1_C_P(SatSystem.GPS, 31, 1, 'L'), // L1 L1C-P
	GPS_L1_C_DP(SatSystem.GPS, 32,1, 'X'), // L1 L1C-(D+P)

	GLO_L1C(SatSystem.GLONASS, 2, 1, 'C'), // G1 C/A
	GLO_L1P(SatSystem.GLONASS, 3, 1, 'P'), // G1 P
	GLO_L2C(SatSystem.GLONASS, 8, 2, 'C'), // G2 C/A
	GLO_L2P(SatSystem.GLONASS, 9, 2, 'P'), // G2 P

	GAL_E1C(SatSystem.GALILEO, 2, 1, 'C'), // E1 C no data
	GAL_E1A(SatSystem.GALILEO, 3, 1, 'A'), // E1 A
	GAL_E1B(SatSystem.GALILEO, 4, 1, 'B'), // E1 B I/NAV OS/CS/SoL
	GAL_E1BC(SatSystem.GALILEO, 5, 1, 'X'), // B+C
	GAL_E1ABC(SatSystem.GALILEO, 6, 1, 'Z'), // E1 A+B+C
	GAL_E6C(SatSystem.GALILEO, 8, 6, 'C'), // E6 C
	GAL_E6A(SatSystem.GALILEO, 9, 6, 'A'), // E6 A
	GAL_E6B(SatSystem.GALILEO, 10, 6, 'B'), // E6 B
	GAL_E6BC(SatSystem.GALILEO, 11, 6, 'X'), // E6 B+C
	GAL_E6ABC(SatSystem.GALILEO, 12, 6, 'Z'), // E6 A+B+C
	GAL_E5BI(SatSystem.GALILEO, 14, 7, 'I'), // E5B
	GAL_E5BQ(SatSystem.GALILEO, 15, 7, 'Q'), // E5B
	GAL_E5BIQ(SatSystem.GALILEO, 16, 7, 'X'), // E5B
	GAL_E5ABI(SatSystem.GALILEO, 18, 8, 'I'), // E5(A+B)
	GAL_E5ABQ(SatSystem.GALILEO, 19, 8, 'Q'), // E5(A+B)
	GAL_E5ABIQ(SatSystem.GALILEO, 20, 8, 'X'), // E5(A+B)
	GAL_E5AI(SatSystem.GALILEO, 22, 5, 'I'), // E5A I
	GAL_E5AQ(SatSystem.GALILEO, 23, 5, 'Q'), // E5A Q
	GAL_E5AIQ(SatSystem.GALILEO, 24, 5, 'X'), // E5A I+Q

	QZS_L1C(SatSystem.QZSS, 2, 1,'C'), // L1 C/A
	QZS_LEXS(SatSystem.QZSS, 9, 6,'S'), // LEX S
	QZS_LEXL(SatSystem.QZSS, 10, 6,'L'), // LEX L
	QZS_LEXSL(SatSystem.QZSS, 11, 6,'X'), // LEX S+L
	QZS_L2CM(SatSystem.QZSS, 15, 2,'S'), // L2 L2C(M)
	QZS_L2CL(SatSystem.QZSS, 16, 2,'L'), // L2 L2C(L)
	QZS_L2CML(SatSystem.QZSS, 17, 2,'X'), // L2 L2C(M+L)
	QZS_L5I(SatSystem.QZSS, 22, 5,'I'), // L5 I
	QZS_L5Q(SatSystem.QZSS, 23, 5,'Q'), // L5 Q
	QZS_L5IQ(SatSystem.QZSS, 24, 5,'X'), // L5 I+Q
	QZS_L1CD(SatSystem.QZSS, 30, 1,'S'), // L1 L1C(D)
	QZS_L1CP(SatSystem.QZSS, 31, 1,'L'), // L1 L1C(P)
	QZS_L1CDP(SatSystem.QZSS, 32, 1,'X'), // L1 L1C(D+P)

	BDS_1I(SatSystem.BDS, 2, 2, 'I'), // B1 I
	BDS_1Q(SatSystem.BDS, 3, 2, 'Q'), // B1 Q
	BDS_1IQ(SatSystem.BDS, 4, 2, 'X'), // B1 I+Q
	BDS_3I(SatSystem.BDS, 8, 6, 'I'), // B3 I
	BDS_3Q(SatSystem.BDS, 9, 6, 'Q'), // B3 Q
	BDS_3IQ(SatSystem.BDS, 10, 6, 'X'), // B3 I+Q
	BDS_2I(SatSystem.BDS,14, 7, 'I'), // B2 I
	BDS_2Q(SatSystem.BDS, 15, 7, 'Q'), // B2 Q
	BDS_2IQ(SatSystem.BDS, 16, 7, 'X'), // B2 I+Q
	;

	val frequency0:Long
	val frequencyStep:Long
	init {
		var fs = 0L
		frequency0 = when (gnss) {
			SatSystem.GPS -> when (rinexBand) {
				1 -> GnssConstants.GPS_L1_FREQUENCY
				2 -> GnssConstants.GPS_L2_FREQUENCY
				5 -> GnssConstants.GPS_L5_FREQUENCY
				else -> null
			}
			SatSystem.GLONASS -> when (rinexBand) {
				1 -> {
					fs = GnssConstants.GLO_G1_FREQ_STEP
					GnssConstants.GLO_G1_FREQUENCY_0
				}
				2 -> {
					fs = GnssConstants.GLO_G2_FREQ_STEP
					GnssConstants.GLO_G2_FREQUENCY_0
				}
				3 -> {
					fs = GnssConstants.GLO_G2_FREQ_STEP
					GnssConstants.GLO_G2_FREQUENCY_0
				}
				4 -> GnssConstants.GLO_G1A_FREQUENCY
				6 -> GnssConstants.GLO_G2A_FREQUENCY
				else -> null
			}
			SatSystem.GALILEO -> when (rinexBand) {
				1 -> GnssConstants.GAL_E1_FREQUENCY
				5 -> GnssConstants.GAL_E5a_FREQUENCY
				7 -> GnssConstants.GAL_E5b_FREQUENCY
				8 -> GnssConstants.GAL_E5_FREQUENCY
				6 -> GnssConstants.GAL_E6_FREQUENCY
				else -> null
			}
			SatSystem.SBAS -> when (rinexBand) {
				1 -> GnssConstants.SBAS_L1_FREQUENCY
				5 -> GnssConstants.SBAS_L5_FREQUENCY
				else -> null
			}
			SatSystem.QZSS -> when (rinexBand) {
				1 -> GnssConstants.QZSS_L1_FREQUENCY
				2 -> GnssConstants.QZSS_L2_FREQUENCY
				5 -> GnssConstants.QZSS_L5_FREQUENCY
				6 -> GnssConstants.QZSS_L6_FREQUENCY
				else -> null
			}
			SatSystem.BDS -> when (rinexBand) {
				2 -> GnssConstants.BDS_B1I_FREQUENCY
				7 -> GnssConstants.BDS_B2B_FREQUENCY
				6 -> GnssConstants.BDS_B3_FREQUENCY
				else -> null
			}
		} ?: error("Not a valid band: $gnss L$rinexBand${rinexCode?:""}")
		frequencyStep = fs
	}

	fun frequency(fqidx:Int):Long = frequency0 + fqidx*frequencyStep
	fun wavelength(fqidx:Int):Double = GnssConstants.C/frequency(fqidx)

	companion object {
		fun byCode(gnss: SatSystem,code: Int) = values().find { it.code==code && gnss == it.gnss }

	}
}
