package com.example.gadsnotchers.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadsnotchers.R
import com.example.gadsnotchers.domain.GaadsRepository
import com.example.gadsnotchers.network.Network2
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubmissionViewModel(val context: Context) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)



    val firstName = ObservableField("")
    val emailAddress = ObservableField("")
    val lastName = ObservableField("")
    val linkToProject = ObservableField("")


    private val _errorFirstName = MutableLiveData<String>()
    val errorFirstName : LiveData<String>
    get() = _errorFirstName

    private val _errorLastName = MutableLiveData<String>()
    val errorLastName : LiveData<String>
    get() = _errorLastName

    private val _errorEmailAddress = MutableLiveData<String>()
    val errorEmailAddress : LiveData<String>
    get() = _errorEmailAddress

    private val _errorLinkToProject = MutableLiveData<String>()
    val errorLinkToProject : LiveData<String>
    get() = _errorLinkToProject



    fun submitResponse() {
        if (firstName.toString().trim().isEmpty()){
            _errorFirstName.value = context.getString(R.string.field_cannot_be_empty)
        } else if (lastName.toString().trim().isEmpty()){
            context.getString(R.string.field_cannot_be_empty)
        }else if (emailAddress.toString().trim().isEmpty()){
            context.getString(R.string.field_cannot_be_empty)
        } else if (linkToProject.toString().trim().isEmpty()){
            context.getString(R.string.field_cannot_be_empty)
        } else{
            runResponse()
        }
    }

    private fun validationChecks() {
        if (firstName.toString().trim().isEmpty()){
            _errorFirstName.value = context.getString(R.string.field_cannot_be_empty)
        } else if (lastName.toString().trim().isEmpty()){
            context.getString(R.string.field_cannot_be_empty)
        }else if (emailAddress.toString().trim().isEmpty()){
            context.getString(R.string.field_cannot_be_empty)
        } else if (linkToProject.toString().trim().isEmpty()){
            context.getString(R.string.field_cannot_be_empty)
        } else{
            runResponse()
        }
    }

    private fun runResponse() {
        uiScope.launch {
            Network2.sendResponseService.postResponse(
                "Test","Test","Test","Test"
            ).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    //TODO
                    Log.d("Atoms", "No Success")
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    //TODO

                    Log.d("Atoms", firstName.get()?.trim().toString())
                }

            })
        }
    }


    override fun onCleared() {
        viewModelJob.cancel()
    }
}