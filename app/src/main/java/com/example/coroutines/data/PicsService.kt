package com.example.coroutines.data

import com.example.coroutines.data.PicData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PicsService {

    @GET("/photos/")
    fun getPics() : Call<List<PicData>>

    @GET("/photos/{id}")
    fun getPicById(@Query("id") id : Long) : Call<PicData>
}