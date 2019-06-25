package com.example.coroutines.data.api.interfaces

import com.example.coroutines.data.model.PicData

interface PicsAPI {
    suspend fun getPics() : List<PicData>

    suspend fun getPicById(id : Long) : PicData
}