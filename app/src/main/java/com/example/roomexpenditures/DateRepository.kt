package com.example.roomexpenditures

import androidx.lifecycle.LiveData

class DateRepository(val dDao: dataDao) {

    suspend fun insert(dateData: dateData){
        dDao.insert(dateData)
    }

    fun getData(date:Long):LiveData<List<dateData>>{
      return  dDao.getData(date)
    }

    suspend fun getSum(date:Long):Long{
        return dDao.getSum(date)
    }

    suspend fun isPresent(date:Long,d2:Long):Boolean{
      return  dDao.isPresent(date,d2)
    }

    suspend fun getThisMonthSum(d1:Long,d2:Long):Long{
        return dDao.getThisMonthSum(d1,d2)
    }

}