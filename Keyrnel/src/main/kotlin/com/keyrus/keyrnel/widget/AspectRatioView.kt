package com.keyrus.keyrnel.widget

import android.content.Context
import android.util.AttributeSet
import com.keyrus.keyrnel.R

/**
 * Class : AspectRatioView
 * Information : View with Fix Aspect Ratio
 *
 * Created by Paul Mougin on 21/12/2015.
 */
class AspectRatioView : android.view.View {

    private var aspectRatio: Float = 0.toFloat()
    private var verticalAppending: Boolean = false

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) : super(context)

    /**
     * Constructor
     * @param context
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    /**
     * Constructor
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    /**
     * Initialization with XML attributes
     * @param context
     * @param attrs
     */
    private fun init(context: Context, attrs: AttributeSet) {

        val theme = context.theme
        val array = theme.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioView, 0, 0)

        try {
            aspectRatio = array.getFloat(R.styleable.FixedAspectRatioView_aspectRatio, -1.0f)
            verticalAppending = array.getBoolean(R.styleable.FixedAspectRatioView_verticalAppending, true)
        } finally {
            array.recycle()
        }
    }

    /**
     * Override onMeasure to measure & fit the Aspect Ratio
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (aspectRatio < 0.0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }

        val originalWidth = MeasureSpec.getSize(widthMeasureSpec)
        val originalHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (verticalAppending) {
            val calculatedHeight = (originalWidth * aspectRatio).toInt()
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(calculatedHeight, MeasureSpec.EXACTLY))
        } else {
            val calculatedWidth = (originalHeight * aspectRatio).toInt()
            super.onMeasure(MeasureSpec.makeMeasureSpec(calculatedWidth, MeasureSpec.EXACTLY), heightMeasureSpec)
        }
    }
}
