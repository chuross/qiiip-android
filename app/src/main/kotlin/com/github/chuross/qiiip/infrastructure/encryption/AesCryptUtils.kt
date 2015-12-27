package com.github.chuross.qiiip.infrastructure.encryption

import com.tozny.crypto.android.AesCbcWithIntegrity

class AesCryptUtils {

    private constructor()

    companion object {

        fun encrypt(text: String, keyString: String): String = AesCbcWithIntegrity.encrypt(text, AesCbcWithIntegrity.keys(keyString)).toString()

        fun decrypt(text: String, keyString: String): String {
            val civ = AesCbcWithIntegrity.CipherTextIvMac(text)
            val keys = AesCbcWithIntegrity.keys(keyString)
            return AesCbcWithIntegrity.decryptString(civ, keys)
        }
    }

}