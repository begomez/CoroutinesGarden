package com.example.coroutines.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class SecInterceptor : Interceptor {

    val TAG = SecInterceptor::class.java.simpleName

    val TOKEN = "6f7f689019104475a77c8d558f972a821a1d94201bd5ef0c9357abe5d97f7aaa"

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        try {
            var modified = original.newBuilder().apply {
                header("Authorization", "Client-ID $TOKEN")
                method(original.method, original.body)
            }.build()

            return chain.proceed(modified)

        } catch (e : Exception) {
            Log.e(TAG, "intercept", e)

            return chain.proceed(original)
        }
    }
}