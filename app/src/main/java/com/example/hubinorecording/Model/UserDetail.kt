package com.example.hubinorecording.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class UserDetail(var userName:String = "" ,var mobileNumber: String = "", var password:String = "") {
    @PrimaryKey(autoGenerate = true) var id :Int = 0
}