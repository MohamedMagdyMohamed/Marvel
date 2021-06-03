package com.example.marvel.data

import com.example.marvel.BuildConfig
import com.example.marvel.utils.extentions.toHexString
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val request = chain.request()
        val authenticatedRequest = request.url.newBuilder()
            .addQueryParameter(TIMESTAMP, timestamp)
            .addQueryParameter(API_KEY, BuildConfig.API_PUBLIC_KEY)
            .addQueryParameter(HASH, getHash(timestamp))
            .build()

        return chain.proceed(request.newBuilder().url(authenticatedRequest).build())
    }

    private fun getHash(timestamp: String): String {
        val value = timestamp + BuildConfig.API_PRIVATE_KEY + BuildConfig.API_PUBLIC_KEY
        return toMD5(value)
    }

    private fun toMD5(value: String): String {
        val bytes = MessageDigest.getInstance(MD5_ALGORITHM).digest(value.toByteArray())
        return bytes.toHexString()
    }

    companion object {
        private const val API_KEY = "apikey"
        private const val TIMESTAMP = "ts"
        private const val HASH = "hash"

        private const val MD5_ALGORITHM = "MD5"
    }
}
