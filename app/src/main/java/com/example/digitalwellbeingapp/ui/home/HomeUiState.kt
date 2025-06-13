package com.example.digitalwellbeingapp.ui.home

import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo

data class HomeUiState(
    val isLoading: Boolean = false,
    val totalScreenTimeToday:Long = 0L,
    val notificationCountToday:Long = 0L,
    val unlockCountToday:Long = 0L,
    val appUsageInfo:List<AppUsageInfo> = emptyList<AppUsageInfo>(),
    val error:String? = null
)