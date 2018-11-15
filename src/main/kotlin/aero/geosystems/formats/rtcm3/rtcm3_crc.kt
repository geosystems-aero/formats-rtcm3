package aero.geosystems.formats.rtcm3

import java.nio.ByteBuffer

fun rtcm3_crc(buf: ByteBuffer, offset: Int=0, length: Int=buf.limit()): Int {
	var crc = 0
	for (i in offset..offset + length - 1) {
		val ub = buf.get(i).toInt() and 0xff
		crc = crc xor (ub shl 16)
		for (j in 0..7) {
			crc = crc shl 1
			if (crc and 0x1000000 != 0)
				crc = crc xor 0x01864cfb
		}
	}
	return crc
}

fun rtcm3_crc(buf: ByteArray, offset:Int = 0, length:Int = buf.size): Int {
	return rtcm3_crc(ByteBuffer.wrap(buf), offset, length)
}
