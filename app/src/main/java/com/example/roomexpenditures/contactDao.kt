package com.example.roomexpenditures

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface contactDao {

    @Insert
    suspend fun insert(contact: contact)

    @Delete
    suspend fun delete(contact: contact)

    @Query("Select * From contactData")
    fun getAllData():LiveData<List<contact>>

}