package com.example.digitalwellbeingapp.core.util


import android.R.attr.end
import java.util.Calendar
import java.util.TimeZone

fun getStartEndOfDay(
    month:Int,
    dayOfMonth:Int,
    timeZone: TimeZone = TimeZone.getDefault(),
    year:Int = Calendar.getInstance().get(Calendar.YEAR)
):Pair<Long,Long>{
    val now = Calendar.getInstance(timeZone)
    val calendar = Calendar.getInstance(timeZone).apply {
        set(Calendar.YEAR,year)
        set(Calendar.MONTH,month)
        set(Calendar.DAY_OF_MONTH,dayOfMonth)
        set(Calendar.HOUR_OF_DAY,0)
        set(Calendar.MINUTE,0)
        set(Calendar.SECOND,0)
        set(Calendar.MILLISECOND,0)
    }
    val start = calendar.timeInMillis
    val isToday = now.get(Calendar.MONTH) == month &&
            now.get(Calendar.DAY_OF_MONTH) == dayOfMonth &&
            now.get(Calendar.YEAR) == year
    val end = if(isToday){
        now.timeInMillis
    }else {
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.timeInMillis
    }
    return start to end
}
fun getStartEndOfWeek(
    weekOfYear:Int,
    timeZone: TimeZone = TimeZone.getDefault()
):Pair<Long, Long>{
    val now = Calendar.getInstance(timeZone)
    val calendar = Calendar.getInstance(timeZone).apply {
        set(Calendar.WEEK_OF_YEAR,weekOfYear)
        set(Calendar.DAY_OF_WEEK,firstDayOfWeek)
        set(Calendar.HOUR_OF_DAY,0)
        set(Calendar.MINUTE,0)
        set(Calendar.SECOND,0)
        set(Calendar.MILLISECOND,0)
    }
    val start = calendar.timeInMillis
    val currentWeek = now.get(Calendar.WEEK_OF_YEAR)
    val currentYear = now.get(Calendar.YEAR)
    val isCurrentWeek = weekOfYear == currentWeek &&
            calendar.get(Calendar.YEAR) == currentYear

    val end = if(isCurrentWeek){
        now.timeInMillis
    }else{
        calendar.apply {
            add(Calendar.DAY_OF_WEEK,6)
            set(Calendar.HOUR_OF_DAY,23)
            set(Calendar.MINUTE,59)
            set(Calendar.SECOND,59)
            set(Calendar.MILLISECOND,999)
        }.timeInMillis
    }

    return start to end
}