package com.keyrus.keyrnel.widget.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Class : HandleBackKeyEditText
 * Information : EditText which lost the focus when BackKey is pressed
 *
 * Created by Paul.Mougin on 11/09/2015.
 */
class HandleBackKeyEditText(context: Context, attrs: AttributeSet) : EditText(context, attrs) {

    /**
     * Override to handle Back Key Event
     * @param keyCode
     * @param event
     * @return
     */
    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //User has pressed Back Key so Hide the keyboard
            val mgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mgr.hideSoftInputFromWindow(this.windowToken, 0)
            this.clearFocus()
            return true
        }
        return false
    }
}
