package com.example.coroutines.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


class MainFragment : Fragment() {

    val mainFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setListeners()
    }

    override fun onStart() {
        super.onStart()

        this.fetchData()
    }

    private fun fetchData() {
        try {
            showProgress()

            this.mainFragmentViewModel.getComics()

        } catch (e : Exception) {

        } finally {
            hideProgress()
        }
    }

    private fun setListeners() {
        this.mainFragmentViewModel.data.observe(this,
            Observer<List<PicView>> { t -> refreshList(t!!) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_main, container, false)

    private fun refreshList(list : List<PicView>) {
        var adapter = PicsAdapter(list)

        this.list.apply {
            this.layoutManager = GridLayoutManager(this@MainFragment.activity, 1, RecyclerView.VERTICAL, false)
            this.setHasFixedSize(false)
            this.adapter = adapter
        }
    }

    private fun showProgress() {
        this.loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        this.loading.visibility = View.GONE
    }
}