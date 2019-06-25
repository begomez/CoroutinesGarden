package com.example.coroutines.presentation.viewmodel


import androidx.lifecycle.*
import com.example.coroutines.data.model.PicData
import com.example.coroutines.presentation.model.PicView
import com.example.coroutines.repository.impl.PicsRepositoryImpl
import com.example.coroutines.repository.interfaces.PicsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Viewmodel that saves state for list screen
 */
class MainFragmentViewModel(var repo : PicsRepository = PicsRepositoryImpl()) : ViewModel() {

    /**
     * Mutable data stored internally retrieved from source
     */
    private var privateData : MutableLiveData<List<PicView>> = MutableLiveData()

    /**
     * Immutable data available for UI controllers
     */
    val exposedData : LiveData<List<PicView>>
        get() = this.privateData


    /**
     * Retrieve remote list of data
     */
    fun getList() {

        /**
         * "launch()" is a hook for suspend functions. Receives executor as param
         *
         */
        this.viewModelScope.launch(Dispatchers.IO) {
            var retrievedList : List<PicData> = repo.retrieveList()

            //XXX: post data and notify observers
            privateData.postValue(retrievedList.map { PicView(it.id, it.urls.regular) })
        }
    }
}


/**
 * Factory for viewmodel that takes in some params and returns customized viewmodel
 */
class MainFragmentViewModelFactory(var repo : PicsRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        super.create(modelClass)

        return MainFragmentViewModel(this.repo) as T
    }
}