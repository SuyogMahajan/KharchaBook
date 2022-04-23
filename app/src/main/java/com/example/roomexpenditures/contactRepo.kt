package com.example.roomexpenditures

class contactRepo(val contactDao:contactDao) {

    val list = contactDao.getAllData()

    suspend fun insert(contact: contact){
        contactDao.insert(contact)
    }

    suspend fun delete(contact: contact){
        contactDao.delete(contact)
    }

}