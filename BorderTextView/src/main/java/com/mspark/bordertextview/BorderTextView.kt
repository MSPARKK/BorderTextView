package com.mspark.bordertextview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.Gravity

class BorderTextView : androidx.appcompat.widget.AppCompatTextView {

    private var stroke = false
    private var strokeWidth = 0.0f
    private var strokeColor = 0
    private val strokePaint: Paint by lazy {
        TextPaint()
    }

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.BorderTextView).apply {
                stroke = getBoolean(R.styleable.BorderTextView_textStroke, false)
                strokeWidth = getFloat(R.styleable.BorderTextView_textStrokeWidth, 0.0f)
                strokeColor = getColor(R.styleable.BorderTextView_textStrokeColor, -0x1)
                recycle()
            }
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


    override fun onDraw(canvas: Canvas) {
        strokePaint.apply {
            strokeWidth = this@BorderTextView.strokeWidth
            textSize = this@BorderTextView.textSize
            typeface = this@BorderTextView.typeface
            color = strokeColor
        }

        val text = text.toString()

        var xPos = width / 2

        when (gravity) {
            Gravity.CENTER -> {
                strokePaint.textAlign = Paint.Align.CENTER
            }
            Gravity.CENTER_VERTICAL + Gravity.LEFT -> {
                strokePaint.textAlign = Paint.Align.LEFT
                xPos = paddingStart
            }
            Gravity.CENTER_VERTICAL + Gravity.RIGHT -> {
                strokePaint.textAlign = Paint.Align.RIGHT
                xPos = width - paddingEnd
            }
            else -> {
                strokePaint.textAlign = Paint.Align.CENTER
            }
        }


        val rect = Rect()

        if (stroke) {
            for (i in 0 until lineCount) {
                val yPos = getLineBounds(i, rect)
                val lineStart = layout.getLineStart(i)
                val lineEnd = layout.getLineEnd(i)
                var lineString = text.substring(lineStart, lineEnd)

                if (!lineString.contains("\n") && i != lineCount - 1) {
                    lineString = lineString.trimEnd()
                }
                lineString = lineString.replace("\n", "")
                canvas.drawText(lineString, xPos.toFloat(), yPos.toFloat(), strokePaint)
            }
        }


        super.onDraw(canvas)
    }

    fun setStrokeColor(colorHex: String?) {
        strokeColor = Color.parseColor(colorHex)
        invalidate()
    }


    fun setStrokeWidth(width: Int) {
        if (!stroke) {
            stroke = true
        }
        strokeWidth = width.toFloat()
        invalidate()
    }

}