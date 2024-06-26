package com.mspark.bordertextview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView

class BorderTextComponent(private val view: TextView) {
    private var stroke = false
    private var strokeWidth = 0.0f
    private var strokeColor = 0
    private var typeface: Typeface? = null


    private val strokePaint: Paint by lazy {
        TextPaint()
    }

    private val textBounds: Rect by lazy {
        Rect()
    }


    fun setup(attrs: AttributeSet?) {
        view.context.obtainStyledAttributes(attrs, R.styleable.BorderTextView).apply {
            stroke = getBoolean(R.styleable.BorderTextView_textStroke, false)
            strokeWidth = getFloat(R.styleable.BorderTextView_textStrokeWidth, 0.0f)
            strokeColor = getColor(R.styleable.BorderTextView_textStrokeColor, -0x1)
            recycle()
        }
        setupPaint()
    }

    private fun setupPaint() {
        strokePaint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    fun onDraw(canvas: Canvas) {
        strokePaint.apply {
            strokeWidth = this@BorderTextComponent.strokeWidth
            textSize = view.textSize
            typeface = this@BorderTextComponent.typeface
            color = strokeColor
        }

        val xPos: Int = when (view.gravity) {
            Gravity.CENTER -> {
                strokePaint.textAlign = Paint.Align.CENTER
                view.width / 2
            }
            Gravity.CENTER_VERTICAL + Gravity.LEFT -> {
                strokePaint.textAlign = Paint.Align.LEFT
                view.paddingStart
            }
            Gravity.CENTER_VERTICAL + Gravity.RIGHT -> {
                strokePaint.textAlign = Paint.Align.RIGHT
                view.width - view.paddingEnd
            }
            else -> {
                strokePaint.textAlign = Paint.Align.LEFT
                view.paddingStart
            }
        }

        if (stroke) {
            for (i in 0 until view.lineCount) {
                val yPos = view.getLineBounds(i, this@BorderTextComponent.textBounds)
                val lineStart = view.layout.getLineStart(i)
                val lineEnd = view.layout.getLineEnd(i)
                var lineString = view.text.substring(lineStart, lineEnd)



                if (!lineString.contains("\n") && i != view.lineCount - 1) {
                    lineString = lineString.trimEnd()
                }
                lineString = lineString.replace("\n", "")

                canvas.drawText(lineString, xPos.toFloat(), yPos.toFloat(), strokePaint)
            }
        }
    }

    fun setStrokeColor(colorHex: String?) {
        strokeColor = Color.parseColor(colorHex)
        view.invalidate()
    }


    fun setStrokeWidth(width: Int) {
        if (!stroke) {
            stroke = true
        }
        strokeWidth = width.toFloat()
        view.invalidate()
    }

    fun setTypeface(typeface: Typeface) {
        this.typeface = typeface
        view.invalidate()
    }
}
