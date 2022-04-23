package com.example.roomexpenditures

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "DateTable")
class dateData (val date:Long,val reason:String,val amount:Long){

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L

}