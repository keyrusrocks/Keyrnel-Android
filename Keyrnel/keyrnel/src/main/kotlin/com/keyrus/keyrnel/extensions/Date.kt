package com.keyrus.keyrnel.extensions

import java.util.*

/**
 * Created by Paul Mougin on 08/10/2018.
 */
fun Date.isWeekEnd(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    return when(dayOfWeek) {
        Calendar.SATURDAY, Calendar.SUNDAY -> true
        else -> false
    }
}

fun Date.getNumberOfDayBeforeEndOfMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val actualDay = calendar.get(Calendar.DAY_OF_MONTH)
    val finalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    return finalDay - actualDay
}

fun Date.isSameDayAs(date: Date): Boolean {
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()
    cal1.time = this
    cal2.time = date
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun Date.withoutTime() : Date {
    val calendar = Calendar.getInstance()
    calendar.time = this

    calendar.set(Calendar.MILLISECOND, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.HOUR_OF_DAY, 0)

    return calendar.time
}