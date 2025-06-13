package com.example.digitalwellbeingapp.core.util

import android.app.usage.UsageStats
import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo

fun UsageStats.toAppUsageInfo(): AppUsageInfo = AppUsageInfo(
    packageName = packageName,
    totalTimeForeground = totalTimeInForeground,
    lastTimeUsed = lastTimeUsed,
    firstTimeStamp = firstTimeStamp,
    lastTimeStamp = lastTimeUsed,
)
