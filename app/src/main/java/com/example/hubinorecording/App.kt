package com.example.hubinorecording

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.hubinorecording.Model.CallRecordDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
       appContext = applicationContext

        val database = CallRecordDatabase.instance
    }

    companion object {
        lateinit  var appContext: Context
    }

   /* val db = Room.databaseBuilder(
        this,
        CallRecordDatabase::class.java, "hubino_record.db"
    ).build()*/
}