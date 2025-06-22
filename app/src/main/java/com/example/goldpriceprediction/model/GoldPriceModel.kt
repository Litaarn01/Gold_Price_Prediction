package com.example.goldpriceprediction.model


import android.content.Context
import android.util.Log
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.nio.MappedByteBuffer

class GoldPriceModel(context: Context) {
    private var interpreter: Interpreter? = null

    init {
        try {
            val model: MappedByteBuffer = FileUtil.loadMappedFile(context, "model_gold_price.tflite")
            val options = Interpreter.Options().apply {
                setNumThreads(2)
            }
            interpreter = Interpreter(model, options)

            val inputShape = interpreter!!.getInputTensor(0).shape()
            Log.d("GoldPriceModel", "Input shape: ${inputShape.contentToString()}")

        } catch (e: Exception) {
            Log.e("GoldPriceModel", "Gagal load model: ${e.message}")
            throw e
        }
    }

    fun predict(input: Array<Array<FloatArray>>): Float {
        val output = Array(1) { FloatArray(1) }
        interpreter?.run(input, output)
        return output[0][0]
    }

    fun close() {
        interpreter?.close()
    }
}
