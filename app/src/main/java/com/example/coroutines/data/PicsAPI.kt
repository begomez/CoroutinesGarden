package com.example.coroutines.data

import com.example.coroutines.data.PicData

interface PicsAPI {
    suspend fun getPics() : List<PicData>

    suspend fun getPicById(id : Long) : PicData
}