package com.mspark.bordertextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<BorderTextView>(R.id.programmatic_text_view).apply {
            val strokeColorHex = "#000000"
            val strokeWidth = 10

            setStrokeColor(strokeColorHex)
            setStrokeWidth(strokeWidth)
        }
    }
}