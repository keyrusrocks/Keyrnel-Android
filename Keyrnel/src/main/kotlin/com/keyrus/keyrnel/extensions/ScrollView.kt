package com.keyrus.keyrnel.extensions

import android.widget.HorizontalScrollView
import android.widget.ScrollView

/**
 * Created by Paul Mougin on 08/10/2018.
 */

fun ScrollView.canScroll(): Boolean {
    val child = this.getChildAt(0)
    if (child != null) {
        val childHeight = child.height
        return this.height < childHeight + this.paddingTop + this.paddingBottom
    }
    return false
}

fun HorizontalScrollView.canScroll(): Boolean {
    val child = this.getChildAt(0)
    if (child != null) {
        val childWidth = child.width
        return this.width < childWidth + this.paddingLeft + this.paddingRight
    }
    return false
}