package com.example.android.animequotes.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("/api/quotes/character")
    suspend fun getQuotes(@Query("name") name:String) : Response<List<QuoteListItem>>

}