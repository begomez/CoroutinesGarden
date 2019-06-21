package com.example.coroutines.repository

import com.example.coroutines.data.PicData


interface PicsRepository {
    suspend fun getComics() : List<PicData>

    suspend fun getComicById(id : Long) : PicData
}