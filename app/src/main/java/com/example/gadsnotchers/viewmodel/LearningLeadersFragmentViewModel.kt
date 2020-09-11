package com.example.gadsnotchers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.gadsnotchers.database.GaadsDatabase
import com.example.gadsnotchers.domain.GaadsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LearningLeadersFragmentViewModel(application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = GaadsDatabase.getInstance(application)
    private val gaaddsRepository = GaadsRepository(database)

    init {
        uiScope.launch {
            gaaddsRepository.refreshHours()
            gaaddsRepository.refreshIQ()
        }
    }

    val hoursData = database.gaadsDao.getHours()
    val iQData = database.gaadsDao.getIQ()

    override fun onCleared() {
        viewModelJob.cancel()
    }
}