package com.example.android.animequotes

import android.app.Application
import android.util.Log
import androidx.work.*
import com.example.android.animequotes.data.retrofit.QuoteApi
import com.example.android.animequotes.data.retrofit.QuoteRetrofit
import com.example.android.animequotes.data.room.AnimeDatabase
import com.example.android.animequotes.repo.QuoteRepo
import com.example.android.animequotes.work.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var repo : QuoteRepo

    override fun onCreate() {
        super.onCreate()
        initialized()
        setWorker()
    }

    private fun setWorker() {
        Log.d("myTag", "setWorker: QuoteWorker started")
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,60,TimeUnit.MINUTES)
            .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialized() {
        val quoteApi = QuoteRetrofit().getInstance().create(QuoteApi::class.java)
        val animeDao = AnimeDatabase.getDatabase(applicationContext).quoteDao()
        repo = QuoteRepo(quoteApi,animeDao,this)
    }
}