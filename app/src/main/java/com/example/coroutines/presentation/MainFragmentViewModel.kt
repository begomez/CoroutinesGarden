package com.example.coroutines.presentation

import androidx.lifecycle.*
import com.example.coroutines.repository.PicsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainFragmentViewModel : ViewModel() {
    var data : MutableLiveData<List<PicView>> = MutableLiveData()

    fun getComics() {
        viewModelScope.launch(Dispatchers.IO) {
            var comics = PicsRepositoryImpl().getComics()

            data.postValue(comics.map { PicView(it.id, it.urls.regular) })
        }
    }
}