package com.example.hubinorecording.ui.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.hubinorecording.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler().postDelayed({
            moveToLoginScreen()
        }, 2000)
    }

    private fun moveToLoginScreen() {
        startActivity(Intent(this, LoginActivity2::class.java))
        finish()
    }
}
