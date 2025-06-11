package com.example.digitalwellbeingapp.core.util


import java.util.Calendar
import java.util.TimeZone

fun getStartEndOfDay(
    month:Int,
    dayOfMonth:Int,
    timeZone: TimeZone = TimeZone.getDefault()
):Pair<Long,Long>{
    val calendar = Calendar.getInstance(timeZone).apply {
        set(Calendar.MONTH,month)
        set(Calendar.DAY_OF_MONTH,dayOfMonth)
        set(Calendar.HOUR_OF_DAY,0)
        set(Calendar.MINUTE,0)
        set(Calendar.SECOND,0)
        set(Calendar.MILLISECOND,0)
    }
    val start = calendar.timeInMillis
    calendar.set(Calendar.HOUR_OF_DAY,23)
    calendar.set(Calendar.MINUTE,59)
    calendar.set(Calendar.SECOND,59)
    calendar.set(Calendar.MILLISECOND,999)
    val end = calendar.timeInMillis
    return start to end
}
fun getStartEndOfWeek(
    weekOfYear:Int,
    timeZone: TimeZone = TimeZone.getDefault()
):Pair<Long, Long>{
    val calendar = Calendar.getInstance(timeZone).apply {
        set(Calendar.WEEK_OF_YEAR,weekOfYear)
        set(Calendar.DAY_OF_WEEK,firstDayOfWeek)
        set(Calendar.HOUR_OF_DAY,0)
        set(Calendar.MINUTE,0)
        set(Calendar.SECOND,0)
        set(Calendar.MILLISECOND,0)
    }
    val start = calendar.timeInMillis
    calendar.set(Calendar.DAY_OF_WEEK,6)
    calendar.set(Calendar.HOUR_OF_DAY,23)
    calendar.set(Calendar.MINUTE,59)
    calendar.set(Calendar.SECOND,59)
    calendar.set(Calendar.MILLISECOND,999)
    val end = calendar.timeInMillis
    return start to end
}