package com.example.gadsnotchers.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gadsnotchers.domain.HoursDataClass
import com.example.gadsnotchers.domain.IQDataClass

@Entity
data class HoursDatabaseEntity constructor(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val sn: Int
)

// << Convert Database objects into HoursDataClass >>
fun List<HoursDatabaseEntity>.asHourModel(): List<HoursDataClass> {
    return map {
        HoursDataClass(
            badgeUrl = it.badgeUrl,
            country = it.country,
            hours = it.hours,
            name = it.name
        )
    }
}

@Entity
data class IQDatabaseEntity constructor(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int,
    @PrimaryKey(autoGenerate = true)
    val sn: Int
)

// << Convert Database objects into IQDataClass >>
fun List<IQDatabaseEntity>.asIQModel(): List<IQDataClass> {
    return map {
        IQDataClass(
            badgeUrl = it.badgeUrl,
            country = it.country,
            name = it.name,
            score = it.score
        )
    }
}