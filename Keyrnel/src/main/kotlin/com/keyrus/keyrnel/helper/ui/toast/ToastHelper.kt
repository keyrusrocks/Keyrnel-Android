package com.keyrus.keyrnel.helper.ui.toast

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Paul Mougin on 24/11/2016.
 */

object ToastHelper {

    /**
     * Show a toast message
     * @param context the App context to show the toast
     * @param message the message to display in the toast
     * @param isLong is the application display the toast long or short
     */
    @JvmOverloads
    fun display(context: Context, message: String, isLong: Boolean = true) {
        Toast.makeText(context, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }

    /**
     * Show a toast with multiple lines
     * @param context The context to use.  Usually your [Application]
     * or [Activity] object.
     * @param texts The list of string to display in the toast.
     * @param isLong is the application display the toast long or short
     */
    fun displayMultiline(context: Context, texts: List<String>, isLong: Boolean) {
        val toast = Toast.makeText(context, "", if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)

        val view = toast.view
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        for (text in texts) {
            val textView = TextView(context)
            textView.text = text
            textView.setTextColor(Color.WHITE)
            textView.setPadding(20, 0, 20, 0)
            textView.gravity = Gravity.CENTER
            layout.addView(textView)
        }
        layout.background = view.background
        layout.invalidate()
        toast.view = layout

        toast.show()
    }
}
