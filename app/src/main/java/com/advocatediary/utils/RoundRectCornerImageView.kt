package com.advocatediary.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView

class RoundRectCornerImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private val radius = 18.0f
    private var path: Path? = null
    private var rect: RectF? = null

    fun RoundRectCornerImageView(context: Context) {
        //   super(context)
        init()
    }

    fun RoundRectCornerImageView(context: Context, attrs: AttributeSet) {
        // super(context, attrs)
        init()
    }

    fun RoundRectCornerImageView(context: Context, attrs: AttributeSet, defStyle: Int) {
        //  super(context, attrs, defStyle)
        init()
    }

    private fun init() {
        path = Path()

    }

    protected override fun onDraw(canvas: Canvas) {
        rect = RectF(0f, 0f, this.getWidth().toFloat(), this.getHeight().toFloat())
        path!!.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(path!!)
        super.onDraw(canvas)
    }
}