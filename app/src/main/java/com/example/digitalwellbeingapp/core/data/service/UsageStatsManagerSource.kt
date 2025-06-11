package com.example.digitalwellbeingapp.core.data.service

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import com.example.digitalwellbeingapp.core.util.getStartEndOfDay
import com.example.digitalwellbeingapp.core.util.getStartEndOfWeek
import java.util.Calendar
import javax.inject.Inject

class UsageStatsManagerSource @Inject constructor(
    private val usageStatsManager: UsageStatsManager
){
    fun getUsageStats(
        interval:Int,
        startTime:Long,
        endTime:Long
    ):List<UsageStats>{
        return usageStatsManager.queryUsageStats(interval,startTime,endTime)
            ?.filter { it.totalTimeInForeground>0 }
            .orEmpty()
    }
    fun getDailyStatsUsage(
        month:Int,
        day:Int
    ):List<UsageStats>{
        val(startTime,endTime)= getStartEndOfDay(month,day)
        return getUsageStats(UsageStatsManager.INTERVAL_DAILY,startTime,endTime)
    }
    fun getWeeklyStatsUsage(week: Int):List<UsageStats>{
        val (startTime,endTime) = getStartEndOfWeek(week)
        return  getUsageStats(UsageStatsManager.INTERVAL_WEEKLY,startTime,endTime)
    }
    fun getAppUsageStatsForPackage(
        packageName:String,
        startTime:Long,
        endTime: Long,
        interval: Int = UsageStatsManager.INTERVAL_DAILY
    ): UsageStats?{
        return usageStatsManager.queryUsageStats(
            interval,
            startTime,
            endTime
        ).orEmpty().find { it.packageName == packageName && it.totalTimeInForeground>0 }
    }

}