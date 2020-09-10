package com.example.gadsnotchers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gadsnotchers.R
import com.example.gadsnotchers.adapters.IQRecyclerViewAdapter
import com.example.gadsnotchers.databinding.FragmentSkillIQBinding
import com.example.gadsnotchers.viewmodel.LearningLeadersFragmentViewModel
import com.example.gadsnotchers.viewmodel.LearningLeadersViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SkillIQFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillIQFragment : Fragment() {

    private lateinit var binding: FragmentSkillIQBinding
    private lateinit var leadersFragmentViewModel: LearningLeadersFragmentViewModel
    private lateinit var learningLeadersViewModelFactory: LearningLeadersViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSkillIQBinding.inflate(inflater)

        val activity = requireNotNull(this.activity)

        learningLeadersViewModelFactory = LearningLeadersViewModelFactory(activity.application)

        leadersFragmentViewModel = ViewModelProvider(this, learningLeadersViewModelFactory).get(
            LearningLeadersFragmentViewModel::class.java
        )

        binding.viewModel = leadersFragmentViewModel
        binding.learningRecyclerView.adapter = IQRecyclerViewAdapter()


        binding.lifecycleOwner = this
        return binding.root
    }


}