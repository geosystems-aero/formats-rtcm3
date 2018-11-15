package aero.geosystems.formats.rtcm3

import aero.geosystems.formats.StructBinding
import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.*


const val PREAMBLE_VALUE: Short = 0xD3

/**
 * Created by aimozg on 27.12.2016.
 * Confidential.
 */
fun <T> T.formatAs(fmt: String): String = fmt.format(Locale.ENGLISH, this)

fun ByteArray.toHexString(): String = ("%0${size * 2}X".format(BigInteger(1, this)))
fun <T> Iterable<T>.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...") =
		joinToString(separator, prefix, postfix, limit, truncated) { format.format(Locale.ENGLISH, it) }

inline fun <T> Iterable<T>.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> Any?)) =
		map { transform(it).formatAs(format) }.joinToString(separator, prefix, postfix, limit, truncated)

fun IntArray.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...") =
		joinToString(separator, prefix, postfix, limit, truncated) { format.format(Locale.ENGLISH, it) }

inline fun IntArray.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Int) -> Any?)) =
		map { transform(it).formatAs(format) }.joinToString(separator, prefix, postfix, limit, truncated)

fun DoubleArray.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...") =
		joinToString(separator, prefix, postfix, limit, truncated) { format.format(Locale.ENGLISH, it) }

inline fun DoubleArray.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Double) -> Any?)) =
		map { transform(it).formatAs(format) }.joinToString(separator, prefix, postfix, limit, truncated)

fun BooleanArray.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...") =
		joinToString(separator, prefix, postfix, limit, truncated) { format.format(Locale.ENGLISH, it) }

inline fun BooleanArray.joinFormatted(format: String = "%s", separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Boolean) -> Any?)) =
		map { transform(it).formatAs(format) }.joinToString(separator, prefix, postfix, limit, truncated)

abstract class Rtcm3MessageDef<BINDING : StructBinding>(val mid_const: Int) : Rtcm3StructDef<BINDING>() {
	val preamble_def = UIntMember(8)
	val reserved0_def = DF001(6)
	val message_length_def = UIntMember(10)
	val message_id_def = DF002()

	val crc_def = tailMember { alignMember(8) { UIntMember(24) } }
}

abstract class Rtcm3Message(_def: Rtcm3MessageDef<*>, bb: ByteBuffer, offset: Int) : StructBinding(_def, bb, offset) {
	var preamble: Int by _def.preamble_def
	/*get() = preamble_def.getValue(buffer)
	set(value) = preamble_def.setValue(value,buffer)*/
	var rtcm3hdr_reserved:Int by _def.reserved0_def
	var message_length: Int by _def.message_length_def
	var message_id: Int by _def.message_id_def

	var crc: Int by _def.crc_def

	val totalMessageSize: Int
		get() = (def.crc_def.pos.end(this) + 7) / 8

	override val def: Rtcm3MessageDef<*> = _def

	override fun toString(): String {
		return "RTCM$message_id,${bodyToString()};" + String.format("%06X", crc)
	}

	open fun bodyToString(): String = "[$preamble,$message_length]"
	fun calcCrc(): Int {
		return rtcm3_crc(buffer, structOffset / 8, totalMessageSize - 3)
	}

	fun complete() {
		preamble = 0xD3
		rtcm3hdr_reserved = 0
		message_length = totalMessageSize-6
		message_id = def.mid_const
		crc = calcCrc()
	}

}

class Rtcm3UnknownMessage(bb: ByteBuffer, offset: Int = 0) : Rtcm3Message(Companion, bb, offset) {
	var data by data_def.safe

	override fun bodyToString(): String {
		return super.bodyToString() + data.toHexString()
	}

	companion object : Rtcm3MessageDef<Rtcm3UnknownMessage>(0) {
		override fun binding(bb: ByteBuffer, structOffset: Int) = Rtcm3UnknownMessage(bb, structOffset)
		val data_def = VarByteArrayMember(message_length_def, -2)
	}
}

