package at.xirado.githook.util

import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object Hmac {
    fun digest(
        msg: String,
        key: String,
        alg: String = "HmacSHA256"
    ): String {
        val signingKey = SecretKeySpec(key.toByteArray(), alg)
        val mac = Mac.getInstance(alg)
        mac.init(signingKey)

        val bytes = mac.doFinal(msg.toByteArray())
        return format(bytes)
    }

    private fun format(bytes: ByteArray): String {
        val formatter = Formatter()
        bytes.forEach { formatter.format("%02x", it) }
        return formatter.toString()
    }
}