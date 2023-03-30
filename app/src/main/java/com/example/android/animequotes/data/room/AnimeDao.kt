package com.example.android.animequotes.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimeDao {

    @Insert
    suspend fun insertQuote(animeQuote: AnimeQuote)

    @Query("DELETE FROM animeQuote")
    suspend fun deleteQuotes()

    @Query("SELECT * FROM animeQuote")
    suspend fun getQuotes() : List<AnimeQuote>

}