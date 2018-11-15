package aero.geosystems.formats.rtcm3

import aero.geosystems.formats.rtcm3.messages.*
import java.util.*

/**
 * Created by aimozg on 09.01.2017.
 * Confidential.
 */

val Rtcm3MessageIDs: Map<Int, Rtcm3MessageDef<out Rtcm3Message>> = mapOf(
		1001 to Rtcm1001.Companion,
		1002 to Rtcm1002.Companion,
		1003 to Rtcm1003.Companion,
		1004 to Rtcm1004.Companion,

		1005 to Rtcm1005.Companion,
		1006 to Rtcm1006.Companion,

		1007 to Rtcm1007.Companion,
		1008 to Rtcm1008.Companion,

		1009 to Rtcm1009.Companion,
		1010 to Rtcm1010.Companion,
		1011 to Rtcm1011.Companion,
		1012 to Rtcm1012.Companion,


		1019 to Rtcm1019.Companion,

		1033 to Rtcm1033.Companion,

		1071 to Rtcm1071.Companion,
		1072 to Rtcm1072.Companion,
		1073 to Rtcm1073.Companion,
		1074 to Rtcm1074.Companion,
		1075 to Rtcm1075.Companion,
		1076 to Rtcm1076.Companion,
		1077 to Rtcm1077.Companion,
		1081 to Rtcm1081.Companion,
		1082 to Rtcm1082.Companion,
		1083 to Rtcm1083.Companion,
		1084 to Rtcm1084.Companion,
		1085 to Rtcm1085.Companion,
		1086 to Rtcm1086.Companion,
		1087 to Rtcm1087.Companion,
		1091 to Rtcm1091.Companion,
		1092 to Rtcm1092.Companion,
		1093 to Rtcm1093.Companion,
		1094 to Rtcm1094.Companion,
		1095 to Rtcm1095.Companion,
		1096 to Rtcm1096.Companion,
		1097 to Rtcm1097.Companion,
		1111 to Rtcm1111.Companion,
		1112 to Rtcm1112.Companion,
		1113 to Rtcm1113.Companion,
		1114 to Rtcm1114.Companion,
		1115 to Rtcm1115.Companion,
		1116 to Rtcm1116.Companion,
		1117 to Rtcm1117.Companion,
		1121 to Rtcm1121.Companion,
		1122 to Rtcm1122.Companion,
		1123 to Rtcm1123.Companion,
		1124 to Rtcm1124.Companion,
		1125 to Rtcm1125.Companion,
		1126 to Rtcm1126.Companion,
		1127 to Rtcm1127.Companion,

		1230 to Rtcm1230.Companion
)
val OBSERVATION_MIDS = Collections.unmodifiableSet(setOf(
		1001,1002,1003,1004,
		1009,1010,1011,1012,
		1071,1072,1073,1074,1075,1076,1077,
		1081,1082,1083,1084,1085,1086,1087,
		1091,1092,1093,1094,1095,1096,1097,
		1111,1112,1113,1114,1115,1116,1117,
		1121,1122,1123,1124,1125,1126,1127))
val EPHEMERIS_MIDS = Collections.unmodifiableSet(setOf(1019,1020))
