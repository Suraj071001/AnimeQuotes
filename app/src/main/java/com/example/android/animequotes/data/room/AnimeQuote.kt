package com.example.android.animequotes.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animeQuote")
data class AnimeQuote(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val anime:String,
    val quote:String,
    val character : String
)
