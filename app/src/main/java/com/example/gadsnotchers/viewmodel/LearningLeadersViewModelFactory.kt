package com.example.gadsnotchers.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LearningLeadersViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LearningLeadersFragmentViewModel::class.java)) {
            return LearningLeadersFragmentViewModel(application) as T
        }

        throw IllegalArgumentException("Unnble to find viewmodel")
    }
}