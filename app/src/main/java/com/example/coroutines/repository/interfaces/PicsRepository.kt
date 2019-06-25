package com.example.coroutines.repository.interfaces

import com.example.coroutines.data.model.PicData


interface PicsRepository {
    suspend fun getComics() : List<PicData>

    suspend fun getComicById(id : Long) : PicData
}