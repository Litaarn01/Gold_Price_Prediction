package com.example.goldpriceprediction

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.goldpriceprediction.R
import com.example.goldpriceprediction.ui.AboutActivity
import com.example.goldpriceprediction.ui.DatasetActivity
import com.example.goldpriceprediction.ui.FiturActivity
import com.example.goldpriceprediction.ui.ModelActivity
import com.example.goldpriceprediction.ui.SimulationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        supportActionBar?.hide()
    }

    fun openAbout(@Suppress("UNUSED_PARAMETER") view: View) {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    fun openDataset(view: View) {
        startActivity(Intent(this, DatasetActivity::class.java))
    }

    fun openFitur(view: View) {
        startActivity(Intent(this, FiturActivity::class.java))
    }

    fun openModel(view: View) {
        startActivity(Intent(this, ModelActivity::class.java))
    }

    fun openSimulasi(view: View) {
        startActivity(Intent(this, SimulationActivity::class.java))
    }
}

