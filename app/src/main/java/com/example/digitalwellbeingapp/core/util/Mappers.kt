package com.example.digitalwellbeingapp.core.util

import android.app.usage.UsageStats
import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo

fun UsageStats.toAppUsageInfo(appName: String?=null): AppUsageInfo = AppUsageInfo(
    packageName = packageName,
    appName = appName,
    totalTimeForeground = totalTimeInForeground,
    lastTimeUsed = lastTimeUsed,
    firstTimeStamp = firstTimeStamp,
    lastTimeStamp = lastTimeUsed
)