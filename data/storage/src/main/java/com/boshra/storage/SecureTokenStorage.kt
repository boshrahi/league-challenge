package com.boshra.storage

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.core.content.edit
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class SecureTokenStorage(context: Context) {

  companion object {
    private const val PREF_NAME = "secure_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN_INITIAL_VECTOR = "refresh_token_iv"
    private const val KEY_ALIAS = "refresh_token_key"
    private const val CIPHER_TRANSFORMATION = "AES/GCM/NoPadding"
    private const val ANDROID_KEYSTORE = "AndroidKeyStore"
    private const val IV_SIZE = 128
  }

  private val prefs: SharedPreferences =
    context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

  private val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }

  init {
    if (!keyStore.containsAlias(KEY_ALIAS)) {
      generateSecretKey()
    }
  }

  private fun generateSecretKey() {
    val keyGenerator = KeyGenerator.getInstance(
      KeyProperties.KEY_ALGORITHM_AES,
      ANDROID_KEYSTORE,
    )
    val keyGenSpec = KeyGenParameterSpec.Builder(
      KEY_ALIAS,
      KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
    )
      .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
      .build()
    keyGenerator.init(keyGenSpec)
    keyGenerator.generateKey()
  }

  private fun getSecretKey(): SecretKey =
    keyStore.getKey(KEY_ALIAS, null) as SecretKey

  private fun encryptToken(plainText: String): Pair<String, String> {
    val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
    cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
    val iv = Base64.encodeToString(cipher.iv, Base64.DEFAULT)
    val encrypted = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
    val cipherText = Base64.encodeToString(encrypted, Base64.DEFAULT)
    return Pair(cipherText, iv)
  }

  private fun decryptToken(cipherText: String, iv: String): String {
    val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
    val ivSpec = GCMParameterSpec(IV_SIZE, Base64.decode(iv, Base64.DEFAULT))
    cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), ivSpec)
    val decoded = Base64.decode(cipherText, Base64.DEFAULT)
    val decrypted = cipher.doFinal(decoded)
    return String(decrypted, Charsets.UTF_8)
  }

  fun getAccessToken(): String? {
    val encrypted = prefs.getString(KEY_ACCESS_TOKEN, null)
    val iv = prefs.getString(KEY_REFRESH_TOKEN_INITIAL_VECTOR, null)
    if (encrypted == null || iv == null) return null
    return try {
      decryptToken(encrypted, iv)
    } catch (e: Exception) {
      null
    }
  }

  fun saveAccessToken(accessToken: String) {
    val (encryptedToken, iv) = encryptToken(accessToken)
    prefs.edit {
      putString(KEY_ACCESS_TOKEN, encryptedToken)
      putString(KEY_REFRESH_TOKEN_INITIAL_VECTOR, iv)
    }
  }

  fun clear() {
    prefs.edit { clear() }
  }
}
