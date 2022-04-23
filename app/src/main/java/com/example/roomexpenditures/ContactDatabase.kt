package com.example.roomexpenditures

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun getDao():contactDao

    companion object{

        @Volatile
        private var INSTANCE:ContactDatabase? = null

        fun getDataBase(context: Context):ContactDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,ContactDatabase::class.java,"Contact_Table").build()
                INSTANCE = instance
                instance
            }

        }

    }

}