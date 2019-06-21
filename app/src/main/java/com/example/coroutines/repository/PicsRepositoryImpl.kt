package com.example.coroutines.repository

import com.example.coroutines.data.PicsAPI
import com.example.coroutines.data.PicsAPIImpl


class PicsRepositoryImpl : PicsRepository {

    private var api : PicsAPI = PicsAPIImpl()

    override suspend fun getComicById(id : Long) = this.api.getPicById(id)

    override suspend fun getComics() = this.api.getPics()
}