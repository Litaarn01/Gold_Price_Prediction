package com.example.goldpriceprediction.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.goldpriceprediction.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            // Arahkan ke WelcomeActivity
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, 3000) // delay 3 detik
    }
}

