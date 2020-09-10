package com.example.gadsnotchers.network

import com.example.gadsnotchers.database.HoursDatabaseEntity
import com.example.gadsnotchers.database.IQDatabaseEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Hours(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    @Json(name = "name")
    val internName: String
)

// Converting network result to database objects

fun List<Hours>.asDataModel() : Array<HoursDatabaseEntity>{
    return map {
        HoursDatabaseEntity(
            badgeUrl = it.badgeUrl,
            country = it.country,
            hours = it.hours,
            name = it.internName
        )
    }.toTypedArray()
}

data class SkillsIQ(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int
)

fun List<SkillsIQ>.asDataModel() : Array<IQDatabaseEntity>{
    return map {
        IQDatabaseEntity(
            badgeUrl = it.badgeUrl,
            country = it.country,
            score = it.score,
            name = it.name
        )
    }.toTypedArray()
}


