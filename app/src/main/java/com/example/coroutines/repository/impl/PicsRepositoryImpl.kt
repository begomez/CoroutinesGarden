package com.example.coroutines.repository.impl

import com.example.coroutines.data.api.interfaces.PicsAPI
import com.example.coroutines.data.api.impl.PicsAPIImpl
import com.example.coroutines.repository.interfaces.PicsRepository


class PicsRepositoryImpl : PicsRepository {

    private var api : PicsAPI = PicsAPIImpl()

    override suspend fun getComicById(id : Long) = this.api.getPicById(id)

    override suspend fun getComics() = this.api.getPics()
}