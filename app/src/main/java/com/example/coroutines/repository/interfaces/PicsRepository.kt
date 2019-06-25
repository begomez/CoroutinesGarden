package com.example.coroutines.repository.interfaces


import com.example.coroutines.data.model.PicData


/**
 * Abstraction for pics repository
 */
interface PicsRepository {

    //XXX: both functions are marked as suspend functions so they are available for coroutines

    /**
     * Retrieve random pics from data source
     */
    suspend fun retrieveList() : List<PicData>

    /**
     * Retrieve pic by id from data source
     */
    suspend fun retrieveElementById(id : Long) : PicData
}