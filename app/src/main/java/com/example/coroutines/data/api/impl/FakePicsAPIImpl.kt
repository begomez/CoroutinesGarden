package com.example.coroutines.data.api.impl


import com.example.coroutines.data.api.interfaces.PicsAPI
import com.example.coroutines.data.model.PicData
import com.example.coroutines.data.model.UrlData


class FakePicsAPIImpl : PicsAPI {

    companion object {
        const val FAKE_LENGTH = 10
        const val FAKE_ID = "1L"
        const val FAKE_URL = "https://images.unsplash.com/photo-1561409695-ce8315e7b9a6?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjc3ODQzfQ"
    }

    override suspend fun fetchPics(): List<PicData> {
        var fakes = ArrayList<PicData>()

        for (i in 1..FAKE_LENGTH) {
            fakes.add(PicData(id = FAKE_ID, urls = UrlData(regular = FAKE_URL)))
        }

        return fakes
    }

    override suspend fun fetchPicById(id: Long) = PicData(FAKE_ID)
}