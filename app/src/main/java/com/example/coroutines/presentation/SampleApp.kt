package com.example.coroutines.presentation


import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.coroutines.workers.SampleWorker


class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        this.configWork()
    }

    private fun configWork() {
        val work = OneTimeWorkRequestBuilder<SampleWorker>().build()

        WorkManager.getInstance().enqueue(work)
    }
}