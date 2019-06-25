package com.example.coroutines.data.rest_services

import com.example.coroutines.data.model.PicData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PicsService {

    @GET("/photos/")
    fun getPics() : Call<List<PicData>>

    @GET("/photos/{id}")
    fun getPicById(@Query("id") id : Long) : Call<PicData>
}