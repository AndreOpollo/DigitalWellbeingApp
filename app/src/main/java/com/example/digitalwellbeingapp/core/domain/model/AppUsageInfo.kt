package com.example.digitalwellbeingapp.core.domain.model

import androidx.compose.ui.graphics.ImageBitmap

data class AppUsageInfo(
    val packageName:String,
    val totalTimeForeground:Long,
    val lastTimeUsed:Long,
    val firstTimeStamp:Long,
    val lastTimeStamp:Long
)