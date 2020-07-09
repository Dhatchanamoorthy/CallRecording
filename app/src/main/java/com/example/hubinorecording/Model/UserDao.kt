package com.example.hubinorecording.Model

import android.service.autofill.UserData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDetail")
    fun  getUserData(): List<UserDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(item : UserDetail)

    @Query("SELECT * FROM UserDetail WHERE userdetail.mobileNumber LIKE :usernumber")
    fun getAccount(usernumber: String): UserDetail
}