package com.example.gadsnotchers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.room.util.DBUtil
import com.example.gadsnotchers.R
import com.example.gadsnotchers.databinding.ActivityProjectSubmissionBinding
import com.example.gadsnotchers.network.Network2
import com.example.gadsnotchers.viewmodel.LearningLeadersFragmentViewModel
import com.example.gadsnotchers.viewmodel.SubmissionViewModel
import kotlinx.android.synthetic.main.activity_project_submission.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmission : AppCompatActivity() {

    private lateinit var submissionViewModel: SubmissionViewModel
    private lateinit var binding: ActivityProjectSubmissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_submission)
        submissionViewModel = SubmissionViewModel(applicationContext)
        binding.viewModel = submissionViewModel

        submissionViewModel.errorFirstName.observe(this, Observer {
            binding.firstName.error = it
        })

        tool_bar.setNavigationIcon(R.drawable.back_button)
        tool_bar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })


    }

}
