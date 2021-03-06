package com.example.coroutines.data.rest_services.factories


import com.example.coroutines.data.rest_services.PicsService
import com.example.coroutines.data.rest_services.interceptors.SecInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceFactory {

    val URL_PICS = "https://api.unsplash.com/"

    val TIMEOUT = 30000L

    public fun createPicsService() : PicsService = createFactory(
        URL_PICS
    ).create(PicsService::class.java)

    private fun createFactory(url : String) : Retrofit {
        var gson : Gson = GsonBuilder().apply {
            setLenient()
        }.create()

        var client = OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            addInterceptor(SecInterceptor())
            retryOnConnectionFailure(true)
        }.build()

        return Retrofit.Builder().client(client).baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }
}