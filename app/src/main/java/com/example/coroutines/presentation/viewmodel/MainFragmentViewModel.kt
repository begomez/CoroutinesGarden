package com.example.coroutines.presentation.viewmodel


import androidx.lifecycle.*
import com.example.coroutines.data.model.PicData
import com.example.coroutines.presentation.model.PicView
import com.example.coroutines.presentation.model.StateView
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
    private var privateData = MutableLiveData<List<PicView>>()

    /**
     * Immutable data available for UI controllers
     */
    val exposedData : LiveData<List<PicView>>
        get() = this.privateData

    /**
     * View state (loading, data, error)
     */
    var exposedState = MutableLiveData<StateView>()//TODO: expose as immutable


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


            startLoad()

            //XXX: swith to back thread...
            withContext(Dispatchers.IO) {
                retrievedList = repo.retrieveList()
            }

            privateData.value = retrievedList?.map { PicView(it.id, it.urls.regular) }

            endLoad()
        }
    }

    private fun startLoad() {
        this.exposedState.value = StateView(loading = true)
    }

    private fun endLoad() {
        this.exposedState.value = StateView(loading = false)
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