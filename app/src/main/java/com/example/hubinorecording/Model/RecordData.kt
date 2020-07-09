package com.example.hubinorecording.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordData( var number: String? = "",
                       var time : String? = "",
                       var url: String? = ""){
    @PrimaryKey(autoGenerate = true) var id : Int = 0

}