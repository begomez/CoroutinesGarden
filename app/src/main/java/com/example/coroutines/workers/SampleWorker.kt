package com.example.coroutines.workers


import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coroutines.repository.impl.PicsRepositoryImpl
import java.lang.Exception


/**
 * Worker class that uses coroutines to execute operations
 */
class SampleWorker(cntxt: Context, params: WorkerParameters) : CoroutineWorker(cntxt, params) {

    val TAG = SampleWorker::class.java.simpleName


    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork()")

        return try {
            var repo = PicsRepositoryImpl()

            for (item in repo.retrieveList()) {
                Log.d(TAG, "Retrieved item $item")
            }

            Result.success()

        } catch (e : Exception) {
            Result.failure()
        }

    }
}