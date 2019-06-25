package com.example.coroutines.data.api.impl

import com.example.coroutines.data.api.interfaces.PicsAPI
import com.example.coroutines.data.model.PicData
import com.example.coroutines.data.rest_services.factories.ServiceFactory
import retrofit2.Call
import retrofit2.Response


class PicsAPIImpl : PicsAPI {

    var service = ServiceFactory.createPicsService()

    override suspend fun getPics(): List<PicData> {

        //sleep(15000)

        var temp : Call<List<PicData>> = this.service.getPics()

        var resp : Response<List<PicData>> = temp.execute()

        return resp.body()!!
    }

    override suspend fun getPicById(id: Long) = PicData("")
}