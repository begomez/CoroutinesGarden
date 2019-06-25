package com.example.coroutines.data.api.interfaces


import com.example.coroutines.data.model.PicData


/**
 * Abstraction for pic service
 */
interface PicsAPI {

    //XXX: both functions are marked as suspend functions so they are available for coroutines

    /**
     * Retrieve random pics from remote serv
     */
    suspend fun fetchPics() : List<PicData>

    /**
     * Retrieve pic by id from remote serv
     */
    suspend fun fetchPicById(id : Long) : PicData
}