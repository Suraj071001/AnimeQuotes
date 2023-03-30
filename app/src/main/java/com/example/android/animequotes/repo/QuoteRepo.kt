package com.example.android.animequotes.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.animequotes.data.retrofit.QuoteApi
import com.example.android.animequotes.data.retrofit.QuoteListItem
import com.example.android.animequotes.data.room.AnimeDao
import com.example.android.animequotes.data.room.AnimeQuote
import com.example.android.animequotes.utils.NetworkUtils
import java.lang.Exception


class QuoteRepo(
    val quoteApi: QuoteApi,
    val animeDao:AnimeDao,
    val context: Context
    ) {

    private var mutableLiveQuote = MutableLiveData<Response<List<QuoteListItem>>>()

    val liveQuote : LiveData<Response<List<QuoteListItem>>>
    get() = mutableLiveQuote

    suspend fun getQuotes(name:String){
        if(NetworkUtils.isOnline(context)){
            mutableLiveQuote.postValue(Response.Loading())
            try {
                val quotes = quoteApi.getQuotes(name)
                if(quotes.body()!=null){
                    mutableLiveQuote.postValue(Response.Success(quotes.body()))
                    animeDao.deleteQuotes()
                    quotes.body()!!.forEach {
                        animeDao.insertQuote(AnimeQuote(0,it.anime,it.quote,it.character))
                    }
                }
            }catch (e:Exception){
                mutableLiveQuote.postValue(Response.Error(e.message.toString()))
            }

        }else{
            val list = mutableListOf<QuoteListItem>()
            animeDao.getQuotes().forEach {
                list.add(QuoteListItem(it.anime,it.character,it.quote))
            }
            mutableLiveQuote.postValue(Response.Success(list))
        }

    }

    suspend fun getBackgroundQuotes(name:String){
        val quote = quoteApi.getQuotes(name)
        if(quote.body()!=null){
            animeDao.deleteQuotes()
            quote.body()!!.forEach {
                animeDao.insertQuote(AnimeQuote(0,it.anime,it.quote,it.character))
            }
        }
    }
}