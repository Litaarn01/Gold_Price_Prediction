package com.example.goldpriceprediction.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.goldpriceprediction.R
import com.example.goldpriceprediction.model.GoldPriceModel
import java.text.NumberFormat
import java.util.*

class SimulationActivity : AppCompatActivity() {

    private lateinit var inputTanggal: EditText
    private lateinit var inputJumlahGram: EditText
    private lateinit var btnPrediksi: Button
    private lateinit var outputHasil: TextView
    private var goldPriceModel: GoldPriceModel? = null
    private var selectedDate: String? = null

    // Nilai min-max sesuai data pelatihan (misalnya dari kolom High/Price)
    private val minHarga = 1046.25f
    private val maxHarga = 1911.60f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        supportActionBar?.title = "Simulasi Prediksi Harga Emas"

        // Inisialisasi komponen UI
        inputTanggal = findViewById(R.id.inputTanggal)
        inputJumlahGram = findViewById(R.id.inputJumlahGram)
        btnPrediksi = findViewById(R.id.btnPrediksi)
        outputHasil = findViewById(R.id.outputHasil)

        // Load model TFLite
        try {
            goldPriceModel = GoldPriceModel(this)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal memuat model: ${e.message}", Toast.LENGTH_LONG).show()
            return
        }

        // Tampilkan date picker saat klik tanggal
        inputTanggal.setOnClickListener {
            showDatePicker()
        }

        btnPrediksi.setOnClickListener {
            val gramInput = inputJumlahGram.text.toString().toFloatOrNull()
            if (gramInput == null || gramInput <= 0f) {
                Toast.makeText(this, "Masukkan jumlah gram yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedDate.isNullOrEmpty()) {
                Toast.makeText(this, "Pilih tanggal prediksi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dummyList = List(60) { gramInput }
            val normalized = normalizeInput(dummyList)
            val modelInput = arrayOf(Array(60) { i -> floatArrayOf(normalized[i]) })

            try {
                val result = goldPriceModel?.predict(modelInput)

                // Denormalisasi nilai prediksi
                val hargaPerGram = result?.let { it * (maxHarga - minHarga) + minHarga }
                val totalHarga = hargaPerGram?.times(gramInput)

                val formatted = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
                    .format(totalHarga ?: 0f)

                outputHasil.text = "Tanggal Prediksi: $selectedDate\nHasil Prediksi Harga Emas: $formatted"

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Prediksi gagal: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Menampilkan dialog tanggal
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                inputTanggal.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    // Normalisasi min-max
    private fun normalizeInput(data: List<Float>): List<Float> {
        val min = data.minOrNull() ?: 0f
        val max = data.maxOrNull() ?: 1f
        return data.map { (it - min) / (max - min + 1e-7f) }
    }

    override fun onDestroy() {
        super.onDestroy()
        goldPriceModel?.close()
    }
}
