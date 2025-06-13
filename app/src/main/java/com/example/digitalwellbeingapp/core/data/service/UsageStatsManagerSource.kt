package com.example.digitalwellbeingapp.core.data.service

import android.app.usage.UsageEvents
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.util.getAllLauncherApps
import com.example.digitalwellbeingapp.core.util.getStartEndOfDay
import com.example.digitalwellbeingapp.core.util.getStartEndOfWeek
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class UsageStatsManagerSource @Inject constructor(
    private val usageStatsManager: UsageStatsManager,
    private val context: Context
){
    private fun getUsageStats(
        startTime:Long,
        endTime:Long
    ):List<AppUsageInfo>{
        val launcherApps = getAllLauncherApps(context)-context.packageName
        val usageMap = mutableMapOf<String, Long>()
        val lastUsedMap = mutableMapOf<String, Long>()
        val firstUsedMap = mutableMapOf<String, Long>()

        val events = usageStatsManager.queryEvents(startTime, endTime)
        val event = UsageEvents.Event()

        var currentPackage: String? = null
        var foregroundStart: Long = 0L
        var isScreenOn =true

        val minSessionDuration = 1000L

        while (events.hasNextEvent()) {
            events.getNextEvent(event)

            when (event.eventType) {
                UsageEvents.Event.ACTIVITY_RESUMED -> {
                    if(isScreenOn){
                    currentPackage = event.packageName
                    foregroundStart = event.timeStamp
                    if (currentPackage in launcherApps) {
                        firstUsedMap.putIfAbsent(currentPackage, event.timeStamp)
                     }
                    }
                }
                UsageEvents.Event.ACTIVITY_PAUSED -> {
                    val pkg = event.packageName
                    if (pkg == currentPackage && pkg in launcherApps) {
                        val duration = event.timeStamp - foregroundStart
                        if (duration > minSessionDuration) {
                            usageMap[pkg] = usageMap.getOrDefault(pkg, 0L) + duration
                            lastUsedMap[pkg] = event.timeStamp
                        }
                        currentPackage = null
                    }
                }

                UsageEvents.Event.SCREEN_NON_INTERACTIVE->{
                    isScreenOn = false
                    if(currentPackage!=null && currentPackage in launcherApps){
                        val duration = event.timeStamp - foregroundStart
                        if(duration>minSessionDuration){
                            usageMap[currentPackage] = usageMap
                                .getOrDefault(currentPackage,0) + duration
                        }
                        currentPackage = null
                    }

                }
                UsageEvents.Event.SCREEN_INTERACTIVE->{
                    isScreenOn = true
                }

            }
        }

        return usageMap.map { (packageName, totalForeground) ->
            AppUsageInfo(
                packageName = packageName,
                totalTimeForeground = totalForeground,
                lastTimeUsed = lastUsedMap[packageName] ?: 0L,
                firstTimeStamp = firstUsedMap[packageName] ?: 0L,
                lastTimeStamp = lastUsedMap[packageName] ?: 0L
            )
        }.sortedByDescending { it.totalTimeForeground }
    }
    fun getDailyStatsUsage(
        month:Int,
        day:Int
    ):List<AppUsageInfo>{
        val(startTime,endTime)= getStartEndOfDay(month,day)
        Log.d("UsageStats", "Querying from ${Date(startTime)} to ${Date(endTime)}")
        return getUsageStats(startTime,endTime)
    }

    fun getWeeklyStatsUsage(week: Int):List<AppUsageInfo>{
        Log.d("UsageStats", "Querying from ${week}")
        val (startTime,endTime) = getStartEndOfWeek(week)
        return  getUsageStats(startTime,endTime)
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