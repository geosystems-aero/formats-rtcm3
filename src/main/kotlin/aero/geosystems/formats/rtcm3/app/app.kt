package aero.geosystems.formats.rtcm3.app

import aero.geosystems.formats.IGnssDataConsumer
import aero.geosystems.formats.rtcm3.Rtcm3Decoder
import aero.geosystems.formats.rtcm3.Rtcm3Message
import aero.geosystems.gnss.GnssUtils
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer

/**
 * Created by aimozg on 30.01.2017.
 * Confidential.
 */
fun main(args: Array<String>) {
	//System.`in`.read()
	var output: FileOutputStream? = null
	val decoder = Rtcm3Decoder(GnssUtils.gpstime(), object : IGnssDataConsumer<Rtcm3Message> {
		override fun consume(message: Rtcm3Message?, buffer: ByteBuffer, timestamp: Long?, type: Int) {
			if (message!=null) {
				println(message.toString())
			}
			/*
			when (message) {
				is Rtcm1019 -> message.rinexlikeOutput(System.out)
				is RtcmCommon_1005_1006 -> {
					val ant_x = message.ant_x
					val ant_y = message.ant_y
					val ant_z = message.ant_z
					// TODO to BLH
				}
			}*/
		}

	})
	for (arg in args) {
		if (arg.startsWith("input=")) System.setIn(FileInputStream(arg.substring("input=".length)))
		if (arg.startsWith("output=")) output = FileOutputStream(arg.substring("output=".length))
	}
	val input = System.`in`

	while (true) {
		try {
			val bb = ByteArray(input.available().let { Math.max(it,1) })
			val i = input.read(bb)
			if (i < 0) break
			decoder.consume(ByteBuffer.wrap(bb,0,i))
		} catch (e: IOException) {
			break
		}
	}
	output?.close()
	//println(RtcmMsmCommon.profile.withIndex().joinToString(separator="\n"){ it.index.formatAs("%2d")+" "+it.value})
}

