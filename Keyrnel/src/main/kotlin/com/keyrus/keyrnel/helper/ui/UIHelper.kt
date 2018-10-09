package com.keyrus.keyrnel.helper.ui

import android.app.Activity
import android.content.Context

/**
 * Class: UIHelper
 * Information: Helper to create basic UI items
 *
 * Created by Paul.Mougin on 02/09/2015.
 */
object UIHelper {
    /**
     * Converts pixels into dp
     * @param pixels pixels to convert
     * @param context activity context
     * @return the dp value
     */
    fun convertPixelsToDp(pixels: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return pixels / (metrics.densityDpi / 160f)
    }

    /**
     * Converts dp into pixels
     * @param dp dp to convert
     * @param context activity context
     * @return the pixels value
     */
    fun convertDpToPixels(dp: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi / 160f)
    }

    /**
     * Converts pixels into sp
     * @param pixels pixels to convert
     * @param context activity context
     * @return the sp value
     */
    fun convertPixelsToSp(pixels: Float, context: Context): Float {
        val resources = context.resources
        val scale = resources.displayMetrics.scaledDensity
        return pixels / scale
    }

    /**
     * Converts sp into pixels
     * @param sp sp to converts
     * @param context activity context
     * @return the pixels value
     */
    fun convertSpToPixels(sp: Float, context: Context): Float {
        val resources = context.resources
        val scale = resources.displayMetrics.scaledDensity
        return sp * scale
    }

    fun getStatusBarHeight(context: Activity): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
