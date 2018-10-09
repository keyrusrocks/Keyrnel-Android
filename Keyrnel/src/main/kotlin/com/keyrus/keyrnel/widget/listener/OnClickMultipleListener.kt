package com.keyrus.keyrnel.widget.listener

import android.view.View
import java.util.*

/**
 * Class : OnClickMultipleListener
 * Info : Allow multiple OnClickMultipleListener
 *
 * Created by Paul Mougin on 21/12/2015.
 */
class OnClickMultipleListener : View.OnClickListener {

    private var listeners: MutableList<View.OnClickListener> = ArrayList()

    /**
     * Add a listener to the existing list
     * @param listener : New added listener
     */
    fun addOnClickListener(listener: View.OnClickListener) {
        listeners.add(listener)
    }

    /**
     * onClick delay to all the listeners in listeners
     * @param view
     */
    override fun onClick(view: View) {
        listeners.forEach {
            it.onClick(view)
        }
    }
}
