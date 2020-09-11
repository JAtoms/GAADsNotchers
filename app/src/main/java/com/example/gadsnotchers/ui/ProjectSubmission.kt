package com.example.gadsnotchers.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import com.example.gadsnotchers.R
import com.example.gadsnotchers.databinding.ActivityProjectSubmissionBinding
import com.example.gadsnotchers.network.Network2
import com.example.gadsnotchers.viewmodel.SubmissionViewModel
import kotlinx.android.synthetic.main.activity_project_submission.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmission : AppCompatActivity() {

    private lateinit var submissionViewModel: SubmissionViewModel
    private lateinit var binding: ActivityProjectSubmissionBinding
    private lateinit var progressDialog: Dialog
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContentView(this, R.layout.activity_project_submission)
        submissionViewModel = SubmissionViewModel(applicationContext)
        binding.viewModel = submissionViewModel

        progressDialog = Dialog(this)

        submissionViewModel.errorFirstName.observe(this, Observer {
            binding.firstName.error = it
            binding.firstName.requestFocus()
        })

        submissionViewModel.errorEmailAddress.observe(this, Observer {
            binding.email.error = it
            binding.email.requestFocus()
        })

        submissionViewModel.errorLastName.observe(this, Observer {
            binding.lastName.error = it
            binding.lastName.requestFocus()
        })

        submissionViewModel.errorLinkToProject.observe(this, Observer {
            binding.projectLink.error = it
            binding.projectLink.requestFocus()
        })

        submissionViewModel.triggerDialog.observe(this, Observer {
            if (it) {
                consentDialog()
                submissionViewModel.setTriggeredDialogToFalse()
            }

        })

        tool_bar.setNavigationIcon(R.drawable.back_button)
        tool_bar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })

    }

    fun consentDialog() {
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setCancelable(false)
        progressDialog.onBackPressed()
        progressDialog.setContentView(R.layout.dialog_confirm)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)



        val cancel = progressDialog.findViewById(R.id.green_success) as ImageView
        cancel.setOnClickListener {
            progressDialog.dismiss()
        }

        val yesButton = progressDialog.findViewById(R.id.yes_button) as Button
        yesButton.setOnClickListener {
            runResponse(
                submissionViewModel.mFirstName.toString(),
                submissionViewModel.mLastName.toString(),
                submissionViewModel.mEmailAddress.toString(),
                submissionViewModel.mLinkToProject.toString()

            )
        }
        progressDialog.show()
    }

    fun successDialog() {
        progressDialog.dismiss()
        val dialogBuilder = AlertDialog.Builder(this)
        val layoutInflater = layoutInflater.inflate(R.layout.dialog_success, null)
        dialogBuilder.setView(layoutInflater)
        dialogBuilder.show()
    }

    fun failedDialog() {
        progressDialog.dismiss()
        val dialogBuilder = AlertDialog.Builder(this)
        val layoutInflater = layoutInflater.inflate(R.layout.dialog_failed, null)
        dialogBuilder.setView(layoutInflater)
        dialogBuilder.show()
    }

    private fun runResponse(
        firstName: String,
        lastName: String,
        email: String,
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
                    progressDialog.dismiss()
                    failedDialog()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        progressDialog.cancel()
                        successDialog()
                    } else {
                        progressDialog.cancel()
                        failedDialog()
                    }
                }
            })
        }
    }

}

