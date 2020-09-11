package com.example.gadsnotchers.viewmodel

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadsnotchers.R
import com.example.gadsnotchers.domain.GaadsRepository
import com.example.gadsnotchers.network.Network2
import kotlinx.android.synthetic.main.dialog_confirm.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class SubmissionViewModel(val context: Context) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val patterns: Pattern = Patterns.EMAIL_ADDRESS


    val firstName = ObservableField("")
    val emailAddress = ObservableField("")
    val lastName = ObservableField("")
    val linkToProject = ObservableField("")

    // Dialogs
    private val progressDialog = Dialog(context)

    fun successDialog(){
        progressDialog.setContentView(R.layout.dialog_confirm)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.show()
    }


    // LiveData

    private val _errorFirstName = MutableLiveData<String>()
    val errorFirstName: LiveData<String>
        get() = _errorFirstName

    private val _errorLastName = MutableLiveData<String>()
    val errorLastName: LiveData<String>
        get() = _errorLastName

    private val _errorEmailAddress = MutableLiveData<String>()
    val errorEmailAddress: LiveData<String>
        get() = _errorEmailAddress

    private val _errorLinkToProject = MutableLiveData<String>()
    val errorLinkToProject: LiveData<String>
        get() = _errorLinkToProject

    private val _dialogSuccessOrFailure = MutableLiveData<Boolean>()
    val dialogSuccessOrFailure : LiveData<Boolean>
    get() = _dialogSuccessOrFailure


    fun submitResponse() {
        successDialog()
        validationChecks()
    }


    private fun validationChecks() {
        when {
            firstName.get().toString().trim().isEmpty() -> {
                _errorFirstName.value = context.getString(R.string.field_cannot_be_empty)
                return
            }
            lastName.get().toString().trim().isEmpty() -> {
                _errorLastName.value = context.getString(R.string.field_cannot_be_empty)
                return
            }
            emailAddress.get().toString().trim().isEmpty() -> {
                _errorEmailAddress.value = context.getString(R.string.field_cannot_be_empty)
                return
            }

            !(patterns.matcher(emailAddress.get().toString().trim()).matches()) -> {
                _errorEmailAddress.value = context.getString(R.string.incorrect_email_format)
                return
            }

            linkToProject.get().toString().trim().isEmpty() -> {
                _errorLinkToProject.value = context.getString(R.string.field_cannot_be_empty)
                return
            }

            else -> {
                runResponse(
                    emailAddress.get().toString().trim(),
                    firstName.get().toString().trim(),
                    lastName.get().toString().trim(),
                    linkToProject.get().toString().trim()
                )
            }
        }
    }

    private fun runResponse(
        email: String,
        firstName: String,
        lastName: String,
        linkProject: String
    ) {
        uiScope.launch {
            Network2.sendResponseService.postResponse(
                email,
                firstName,
                lastName,
                linkProject
            ).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    _dialogSuccessOrFailure.value = false
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    _dialogSuccessOrFailure.value = true
                    successDialog()
                    response.isSuccessful
                }
            })
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}