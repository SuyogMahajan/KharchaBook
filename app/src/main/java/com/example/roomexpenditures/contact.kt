package com.example.roomexpenditures

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactData")
class contact(val name:String, val number:Long,val type:String ,val amount:Long) {
    @PrimaryKey(autoGenerate = true)
     var id:Long = 0L
}
