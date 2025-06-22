package com.example.goldpriceprediction.ui


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goldpriceprediction.R

class ResultActivity : AppCompatActivity() {

    private lateinit var textTanggal: TextView
    private lateinit var textHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.title = "Hasil Prediksi"

        // Inisialisasi komponen UI
        textTanggal = findViewById(R.id.textTanggal)
        textHasil = findViewById(R.id.textHasil)

        // Ambil data dari Intent
        val tanggalPrediksi = intent.getStringExtra("TANGGAL_PREDIKSI")
        val hasilPrediksi = intent.getStringExtra("HASIL_PREDIKSI")

        // Tampilkan data ke UI
        textTanggal.text = "Tanggal Prediksi: ${tanggalPrediksi ?: "Tidak tersedia"}"
        textHasil.text = "Harga Total Diprediksi: ${hasilPrediksi ?: "Tidak tersedia"}"
    }
}
