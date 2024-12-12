package com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils

import java.math.BigInteger
import java.security.MessageDigest

object HashUtil {
    fun getHash(timestamp: String, privateKey: String, publicKey: String): String {
        val hashStr = timestamp + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(hashStr.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }

}