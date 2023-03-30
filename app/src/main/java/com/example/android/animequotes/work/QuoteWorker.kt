package com.example.android.animequotes.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android.animequotes.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, private val params:WorkerParameters) : Worker(context,params) {
    override fun doWork(): Result {
        val quoteRepo = (context as QuoteApplication).repo
        CoroutineScope(Dispatchers.IO).launch {
            quoteRepo.getBackgroundQuotes("m")
        }
        return Result.success()
    }
}