package com.feyverly.mvvmandhilt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.feyverly.mvvmandhilt.data.entities.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun characterDao() : CharacterDao

    companion object{
        @Volatile private var instance : AppDatabase? = null

        fun getDatabase(context:Context) : AppDatabase{
            return instance ?: synchronized(this){ instance ?: buildDatabases(context).also { instance = it }}
        }

        private fun buildDatabases(context: Context)  = Room.databaseBuilder(context,AppDatabase::class.java,"characters")
            .fallbackToDestructiveMigration()
            .build()
    }
}