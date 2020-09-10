package com.example.gadsnotchers.domain

import android.util.Log
import android.widget.Toast
import com.example.gadsnotchers.database.GaadsDatabase
import com.example.gadsnotchers.network.Network.gadsServiice
import com.example.gadsnotchers.network.Network2
import com.example.gadsnotchers.network.Network2.sendResponseService
import com.example.gadsnotchers.network.asDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GaadsRepository(private val database: GaadsDatabase) {

    // Access the Database from here
    suspend fun refreshHours() {
        withContext(Dispatchers.IO) {
            try {
                val hours = gadsServiice.getHours().await()
                database.gaadsDao.insertHours(*hours.asDataModel())
                Log.d("Success", "Inserted successfully")
            } catch (e: Exception) {
                Log.e("GaadsRepository", e.toString())
            }
        }
    }

    suspend fun refreshIQ() {
        withContext(Dispatchers.IO) {
            try {
                val iQSkills = gadsServiice.getIq().await()
                database.gaadsDao.insertIQ(*iQSkills.asDataModel())
                Log.d("Success", "Inserted successfully")
            } catch (e: Exception) {
                Log.e("GaadsRepository", e.toString())
            }
        }
    }
}
