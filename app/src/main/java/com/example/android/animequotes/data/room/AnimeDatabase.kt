package com.example.android.animequotes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnimeQuote::class], version = 1)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun quoteDao() : AnimeDao

    companion object{

        private var INSTANCE : AnimeDatabase? = null

        fun getDatabase(context:Context) : AnimeDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AnimeDatabase::class.java,
                        "quoteDB").build()
                }
            }
            return INSTANCE!!
        }

    }
}