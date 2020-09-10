package com.example.gadsnotchers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gadsnotchers.adapters.MyPagerAdapter
import com.example.gadsnotchers.ui.ProjectSubmission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter
        tabLout.setupWithViewPager(viewpager)

        submit.setOnClickListener {
            startActivity(Intent(this, ProjectSubmission::class.java))
        }
    }
}