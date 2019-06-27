package com.example.coroutines.repository.impl


import com.example.coroutines.data.api.interfaces.PicsAPI
import com.example.coroutines.data.api.impl.PicsAPIImpl
import com.example.coroutines.data.model.PicData
import com.example.coroutines.repository.interfaces.PicsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Implementation for pics repository
 */
class PicsRepositoryImpl(private var api : PicsAPI = PicsAPIImpl()) : PicsRepository {

    override suspend fun retrieveElementById(id : Long) = this.api.fetchPicById(id)

    override suspend fun retrieveList() : List<PicData>  {

        //XXX: change to back thread
        return withContext(Dispatchers.IO) {
             api.fetchPics()
        }
    }
}