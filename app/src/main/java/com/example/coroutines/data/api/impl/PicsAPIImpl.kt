package com.example.coroutines.data.api.impl


import com.example.coroutines.data.api.interfaces.PicsAPI
import com.example.coroutines.data.model.PicData
import com.example.coroutines.data.rest_services.PicsService
import com.example.coroutines.data.rest_services.factories.ServiceFactory
import retrofit2.Call
import retrofit2.Response
import java.lang.Thread.sleep


/**
 * Implementation for pics service
 */
class PicsAPIImpl(var service : PicsService = ServiceFactory.createPicsService()) : PicsAPI {

    val DELAY = 5000L


    override suspend fun fetchPics(): List<PicData> {
        sleep(DELAY)

        return this.service.getPics().await()
    }

    override suspend fun fetchPicById(id: Long) = PicData("")//TODO: implement...
}