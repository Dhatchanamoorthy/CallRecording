package com.example.hubinorecording.Model

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hubinorecording.App

@Database(entities = [RecordData::class,UserDetail::class],version = 1,exportSchema = false)
abstract class CallRecordDatabase : RoomDatabase() {
    abstract fun RecordDao() : RecordDao
    abstract fun UserDao() : UserDao

    companion object {
        val instance: CallRecordDatabase by lazy {
            Log.d("Room db","Preparing new instance")
            Room.databaseBuilder(App.appContext, CallRecordDatabase::class.java, "hubino_call.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}