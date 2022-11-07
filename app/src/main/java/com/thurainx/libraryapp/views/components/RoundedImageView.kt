package com.thurainx.libraryapp.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.withStyledAttributes
import com.thurainx.libraryapp.R

class RoundedImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {


    var cornerRadius = floatArrayOf(
        80f, 80f,   // Top left radius in px
        80f, 80f,   // Top right radius in px
        0f, 0f,     // Bottom right radius in px
        0f, 0f      // Bottom left radius in px
    )
    var cornerRadius2 = 0.0f
    var path = Path()

    init {
        context.withStyledAttributes(attrs,R.styleable.RoundedImageView){
            cornerRadius2 = getDimension(R.styleable.RoundedImageView_cornerRadius, cornerRadius2)
        }
    }


    override fun onDraw(canvas: Canvas?) {
        val rectangle = RectF( 0f,  0f,width.toFloat(),  height.toFloat())
        path.addRoundRect(rectangle,cornerRadius2, cornerRadius2,Path.Direction.CCW)

        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}