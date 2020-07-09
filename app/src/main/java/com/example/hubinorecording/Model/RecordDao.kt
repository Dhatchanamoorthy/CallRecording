package com.example.hubinorecording.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {
    @Query("SELECT * FROM recorddata")
    fun  getRecordData(): List<RecordData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(item : RecordData)

}