package com.keyrus.keyrnel.database

import android.database.Cursor
import android.database.CursorWrapper

/**
 * Created by Paul Mougin on 08/10/2018.
 */
class InverseCursor(cursor: Cursor): CursorWrapper(cursor) {

    override fun moveToFirst(): Boolean {
        return super.moveToLast()
    }

    override fun isAfterLast(): Boolean {
        return super.isBeforeFirst()
    }

    override fun isBeforeFirst(): Boolean {
        return super.isAfterLast()
    }

    override fun isFirst(): Boolean {
        return super.isLast()
    }

    override fun isLast(): Boolean {
        return super.isFirst()
    }

    override fun moveToLast(): Boolean {
        return super.moveToFirst()
    }

    override fun moveToPosition(position: Int): Boolean {
        return super.moveToPosition(count - 1 - position)
    }

    override fun moveToNext(): Boolean {
        return super.moveToPrevious()
    }

    override fun moveToPrevious(): Boolean {
        return super.moveToNext()
    }

    override fun getPosition(): Int {
        return count - 1 - super.getPosition()
    }
}