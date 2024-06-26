package com.mspark.bordertextview

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import java.io.File

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


    /**
     * 삼성폰 안드로이드 14, One Ui 6.1에서 발견된 시스템 폰트를 바꿨을 경우 시스템 폰트의 typeface로 고정되는 버그 처리
     * 이 함수를 써서 TextView나 EditText의 font를 변경해야함.
     */
    private fun setTextFont(fontFileName: String) {

        val typeface = Typeface.createFromAsset(
            assets,
            "fonts" + File.separator + fontFileName
        )

        if (typeface != null) {
            findViewById<BorderTextView>(R.id.programmatic_text_view).setTypeFace(typeface)
        }
    }
}