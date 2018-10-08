package com.keyrus.keyrnel.widget.viewpager

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Class : Lockable View Pager.
 * Information : Classic ViewPager that can be locked.
 *
 * Created by Paul Mougin on 10/12/2015.
 */
class LockableViewPager : ViewPager {
    private var isPagingEnabled = true

    /**
     * Constructor.
     * @param context
     */
    constructor(context: Context) : super(context)

    /**
     * Constructor.
     * @param context
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * Override onTouchEvent to lock if needed.
     * @param ev
     * @return
     */
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isPagingEnabled && super.onTouchEvent(ev)
    }

    /**
     * Override onInterceptTouchEvent to lock if needed.
     * @param ev
     * @return
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isPagingEnabled && super.onInterceptTouchEvent(ev)
    }

    /**
     * Enable or lock paging.
     * @param pagingEnabled
     */
    fun setPagingEnabled(pagingEnabled: Boolean) {
        isPagingEnabled = pagingEnabled
    }
}
