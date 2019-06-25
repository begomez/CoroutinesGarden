package com.example.coroutines.workers


import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


/**
 * Worker class that uses coroutines to execute operations
 */
class TestWorker(cntxt: Context, params: WorkerParameters) : CoroutineWorker(cntxt, params) {

    val TAG = TestWorker::class.java.simpleName


    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork()")

        return Result.success()
    }
}