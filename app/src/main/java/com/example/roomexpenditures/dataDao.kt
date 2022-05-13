package com.example.roomexpenditures

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface dataDao {

    @Insert
   suspend fun insert(dateData: dateData)

   @Query("Select * From DateTable Where date = :date")
   fun getData(date:Long) : LiveData<List<dateData>>

   @Query("Select Sum(amount) From DateTable where date = :date")
   suspend fun getSum(date:Long):Long

   @Query("Select Exists(Select * From DateTable where date >= :date and date <= :date2)")
   suspend fun isPresent(date:Long,date2:Long):Boolean

   @Query("Select Sum(amount) From DateTable where date >= :date1 and date <= :date2")
   suspend fun getThisMonthSum(date1:Long,date2:Long):Long

   @Delete
   suspend fun deleteData(dateData: dateData)

}