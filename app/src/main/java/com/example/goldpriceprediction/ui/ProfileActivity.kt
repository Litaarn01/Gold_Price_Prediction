package com.example.goldpriceprediction.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.goldpriceprediction.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val backButtonContainer = findViewById<FrameLayout>(R.id.backButtonContainer)
        backButtonContainer.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}