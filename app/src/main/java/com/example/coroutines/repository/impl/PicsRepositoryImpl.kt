package com.example.coroutines.repository.impl


import com.example.coroutines.data.api.interfaces.PicsAPI
import com.example.coroutines.data.api.impl.PicsAPIImpl
import com.example.coroutines.repository.interfaces.PicsRepository


/**
 * Implementation for pics repository
 */
class PicsRepositoryImpl(private var api : PicsAPI = PicsAPIImpl()) : PicsRepository {

    override suspend fun retrieveElementById(id : Long) = this.api.fetchPicById(id)

    override suspend fun retrieveList() = this.api.fetchPics()
}