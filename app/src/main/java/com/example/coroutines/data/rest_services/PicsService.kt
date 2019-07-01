package com.example.coroutines.data.rest_services

import com.example.coroutines.data.model.PicData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PicsService {

    @GET("/photos/")
    fun getPics() : Deferred<List<PicData>>

    @GET("/photos/{id}")
    fun getPicById(@Query("id") id : Long) : Deferred<PicData>
}