package com.example.gadsnotchers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gadsnotchers.MainActivity
import com.example.gadsnotchers.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}