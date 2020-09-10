package com.example.gadsnotchers.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gadsnotchers.domain.HoursDataClass
import com.example.gadsnotchers.domain.IQDataClass


@Dao
interface GaadsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHours(vararg hours: HoursDatabaseEntity)

    @Query("SELECT * from hoursdatabaseentity Order by hours DESC")
    fun getHours(): LiveData<List<HoursDataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIQ(vararg iQ: IQDatabaseEntity)

    @Query("SELECT * from IQDatabaseEntity Order by score DESC")
    fun getIQ(): LiveData<List<IQDataClass>>
}