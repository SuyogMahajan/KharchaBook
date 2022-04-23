package com.example.roomexpenditures

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [dateData::class], version = 1, exportSchema = false)
abstract class DateDatabase:RoomDatabase() {

    abstract fun getDao():dataDao

  companion object{

      @Volatile
      private var INSTANCE:DateDatabase? = null

      fun getDataBase(context: Context):DateDatabase{

          return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(context.applicationContext,DateDatabase::class.java,"Date_Table").build()
              INSTANCE = instance
              instance
          }

      }

  }

}