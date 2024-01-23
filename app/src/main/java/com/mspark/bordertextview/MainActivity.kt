package com.mspark.bordertextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import com.mspark.bordertextviewLib.BorderTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<BorderTextView>(R.id.programmatic_text_view).apply {
            val strokeColorHex = "#000000"
            val strokeWidth = 16

            setStrokeColor(strokeColorHex)
            setStrokeWidth(strokeWidth)
        }

        findViewById<Button>(R.id.test_button).setOnClickListener {
            findViewById<BorderTextView>(R.id.programmatic_text_view).apply {
                val strokeColorHex = "#FF9800"
                val strokeWidth = 20

                setStrokeColor(strokeColorHex)
                setStrokeWidth(strokeWidth)

                gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
            }
        }
    }
}