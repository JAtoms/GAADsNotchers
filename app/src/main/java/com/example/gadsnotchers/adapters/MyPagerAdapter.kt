package com.example.gadsnotchers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gadsnotchers.ui.LearningLeadersFragment
import com.example.gadsnotchers.ui.SkillIQFragment

class MyPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> {
                LearningLeadersFragment()
            }
            else ->
                SkillIQFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Learning Leaders"
            else -> "Skill IQ Leaders"
        }
    }
}