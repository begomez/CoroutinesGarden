package com.example.coroutines.presentation.viewmodel


import androidx.lifecycle.*
import com.example.coroutines.data.model.PicData
import com.example.coroutines.presentation.model.PicView
import com.example.coroutines.repository.impl.PicsRepositoryImpl
import com.example.coroutines.repository.interfaces.PicsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
         * "launch()" is a hook for suspend functions. May receive executor as param
         * or specify executors using "withContext()"
         *
         */
        this.viewModelScope.launch {
            var retrievedList: List<PicData>? = null

        // 1. fetch result...

            //XXX: swith to back thread...
            withContext(Dispatchers.IO) {
                retrievedList = repo.retrieveList()
            }


        // 2. ... and send result to UI thread

            //XXX: not needed, it is redundant, already on main thread
            withContext(Dispatchers.Main) {
                privateData.value = retrievedList?.map { PicView(it.id, it.urls.regular) }
            }
        }
    }
}


/**
 * Factory for viewmodels that takes in some params and returns customized viewmodel
 */
class MainFragmentViewModelFactory(var repo : PicsRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        super.create(modelClass)

        return MainFragmentViewModel(this.repo) as T
    }
}