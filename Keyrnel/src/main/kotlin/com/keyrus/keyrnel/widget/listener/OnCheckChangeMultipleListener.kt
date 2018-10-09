package com.keyrus.keyrnel.widget.listener

import android.widget.RadioGroup
import java.util.*

/**
 * Class : OnCheckChangeMultipleListener
 * Info : Allow multiple OnCheckChangeMultipleListener
 *
 * Created by Paul Mougin on 21/12/2015.
 */
class OnCheckChangeMultipleListener : RadioGroup.OnCheckedChangeListener {

    private var listeners: MutableList<RadioGroup.OnCheckedChangeListener> = ArrayList()

    /**
     * Add a listener to the existing list
     * @param listener : New added listener
     */
    fun addOnCheckedChangeListener(listener: RadioGroup.OnCheckedChangeListener) {
        listeners.add(listener)
    }

    /**
     * onCheckedChanged delay to all the listeners in listeners
     * @param group
     * @param checkedId
     */
    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        listeners.forEach {
            it.onCheckedChanged(group, checkedId)
        }
    }
}
