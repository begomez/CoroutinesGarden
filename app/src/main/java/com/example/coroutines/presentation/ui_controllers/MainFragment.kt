package com.example.coroutines.presentation.ui_controllers


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import com.example.coroutines.presentation.viewmodel.MainFragmentViewModel
import com.example.coroutines.presentation.adapter.PicsAdapter
import com.example.coroutines.presentation.model.PicView
import com.example.coroutines.presentation.viewmodel.MainFragmentViewModelFactory
import com.example.coroutines.repository.impl.PicsRepositoryImpl
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


/**
 * Main fragment displaying a list of images
 */
class MainFragment : Fragment() {

    private val TAG = MainFragment::class.java.simpleName

    /**
     * XXX: init viewmodel 1st time it is requested through factory so we can pass custom params
     */
    private val mainFragmentViewModel by lazy {
        MainFragmentViewModelFactory(PicsRepositoryImpl()).create(MainFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setListeners()
    }

    private fun setListeners() {

        //XXX: observe changes on data stored on vm
        this.mainFragmentViewModel.exposedData.observe(this,
            Observer<List<PicView>> { t -> this.refreshList(t!!) })
    }

    override fun onStart() {
        super.onStart()

        this.fetchData()
    }

    private fun fetchData() {
        try {
            this.mainFragmentViewModel.getList()

        } catch (e : Exception) {
            Log.e(TAG,  "fetchData()", e)

            this.showError()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_main, container, false)

    private fun refreshList(list : List<PicView>) {
        var adapter = PicsAdapter(list)

        this.list.apply {
            val SPAN = 1

            this.layoutManager = GridLayoutManager(this@MainFragment.activity, SPAN, RecyclerView.VERTICAL, false)
            this.setHasFixedSize(false)
            this.adapter = adapter
        }
    }

    private fun showError() {
        //TODO
    }

    private fun showProgress() {
        this.loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        this.loading.visibility = View.GONE
    }
}