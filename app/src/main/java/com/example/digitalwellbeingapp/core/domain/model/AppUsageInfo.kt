package com.example.digitalwellbeingapp.core.domain.model

data class AppUsageInfo(
    val packageName:String,
    val appName:String?,
    val totalTimeForeground:Long,
    val lastTimeUsed:Long,
    val firstTimeStamp:Long,
    val lastTimeStamp:Long
)