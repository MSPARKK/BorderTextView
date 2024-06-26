package com.mspark.bordertextview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet

class BorderEditText : androidx.appcompat.widget.AppCompatEditText {

    private val borderComponent: BorderTextComponent by lazy {
        BorderTextComponent(this)
    }

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(
        context,
        attrs,
        defStyleAttr
    ) {
        borderComponent.setup(attrs)
    }

    override fun onDraw(canvas: Canvas) {
        borderComponent.onDraw(canvas)

        super.onDraw(canvas)
    }

    fun setStrokeColor(colorHex: String?) {
        borderComponent.setStrokeColor(colorHex)
    }


    fun setStrokeWidth(width: Int) {
        borderComponent.setStrokeWidth(width)
    }

    fun setTypeFace(typeface: Typeface) {
        this.typeface = typeface
        borderComponent.setTypeface(typeface)
    }
}