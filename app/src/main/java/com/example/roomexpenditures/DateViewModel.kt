package com.example.roomexpenditures

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class DateViewModel(application:Application):AndroidViewModel(application) {

    val repo:DateRepository

    init {
        val dao = DateDatabase.getDataBase(application).getDao()
        repo = DateRepository(dao)
    }

    fun insert( dateData: dateData) = viewModelScope.launch (Dispatchers.IO){
        repo.insert(dateData)
    }

    fun getData(dt:Long):LiveData<List<dateData>>{
        return repo.getData(dt)
    }

   suspend fun getSum(date:Long):Long{
        return repo.getSum(date)
    }

    suspend fun getThisMonthSum(d1:Long,d2:Long):Long{
        return repo.getThisMonthSum(d1,d2)
    }

    suspend fun isPresent(date:Long,d2:Long):Boolean{
        return  repo.isPresent(date,d2)
    }

    fun deleteData(dateData: dateData)= viewModelScope.launch (Dispatchers.IO){
        repo.deleteData(dateData)
    }

}
