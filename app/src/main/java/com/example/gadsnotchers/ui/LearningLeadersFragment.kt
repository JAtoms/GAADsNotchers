package com.example.gadsnotchers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gadsnotchers.R
import com.example.gadsnotchers.adapters.HoursRecyclerViewAdapter
import com.example.gadsnotchers.databinding.FragmentLearningLeadersBinding
import com.example.gadsnotchers.domain.HoursDataClass
import com.example.gadsnotchers.viewmodel.LearningLeadersFragmentViewModel
import com.example.gadsnotchers.viewmodel.LearningLeadersViewModelFactory

class LearningLeadersFragment : Fragment() {

    private lateinit var binding: FragmentLearningLeadersBinding
    private lateinit var leadersFragmentViewModel: LearningLeadersFragmentViewModel
    private lateinit var learningLeadersViewModelFactory: LearningLeadersViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLearningLeadersBinding.inflate(inflater)

        val activity = requireNotNull(this.activity)

        learningLeadersViewModelFactory = LearningLeadersViewModelFactory(activity.application)

        leadersFragmentViewModel = ViewModelProvider(this, learningLeadersViewModelFactory).get(
            LearningLeadersFragmentViewModel::class.java
        )

        binding.viewModel = leadersFragmentViewModel
        binding.learningRecyclerView.adapter = HoursRecyclerViewAdapter()


        binding.lifecycleOwner = this
        return binding.root
    }
}