package com.example.gadsnotchers.viewmodel

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.Window
import android.widget.Button
import android.widget.ImageView
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


    private val _firstName = MutableLiveData<String>()
    val mFirstName: LiveData<String>
        get() = _firstName

    private val _lastName = MutableLiveData<String>()
    val mLastName: LiveData<String>
        get() = _lastName

    private val _emailAddress = MutableLiveData<String>()
    val mEmailAddress: LiveData<String>
        get() = _emailAddress

    private val _linkToProject = MutableLiveData<String>()
    val mLinkToProject: LiveData<String>
        get() = _linkToProject

    private val _triggerDialog = MutableLiveData<Boolean>()
    val triggerDialog: LiveData<Boolean>
        get() = _triggerDialog

    fun setTriggeredDialogToFalse(){
        _triggerDialog.value = false
    }


    fun submitResponse() {
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
                _triggerDialog.value = true
                _firstName.value = firstName.get().toString().trim()
                _lastName.value = lastName.get().toString().trim()
                _errorEmailAddress.value = emailAddress.get().toString().trim()
                _errorLinkToProject.value = linkToProject.get().toString().trim()
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

                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                }
            })
        }
    }


    override fun onCleared() {
        viewModelJob.cancel()
    }
}