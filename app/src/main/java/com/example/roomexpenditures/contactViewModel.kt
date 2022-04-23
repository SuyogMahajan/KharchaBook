package com.example.roomexpenditures

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class contactViewModel(application: Application):AndroidViewModel(application) {

    var list :LiveData<List<contact>>
    var repo:contactRepo

    init {

        val dao:contactDao = ContactDatabase.getDataBase(application.applicationContext).getDao()
        repo = contactRepo(dao)
        list = repo.list

    }

    fun insert(contact: contact)  = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(contact)
    }

   fun delete(contact: contact) = viewModelScope.launch(Dispatchers.IO){
        repo.delete(contact)
    }

}