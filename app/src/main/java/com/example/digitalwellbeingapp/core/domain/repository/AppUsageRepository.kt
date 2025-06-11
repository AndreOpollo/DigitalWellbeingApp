package com.example.digitalwellbeingapp.core.domain.repository

import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AppUsageRepository {
    suspend fun getAppUsageStatsByPackage(
        packageName:String,
        startTime:Long,
        endTime:Long,
        interval: Int
    ):Flow<Resource<AppUsageInfo?>>
    suspend fun getDailyAppUsageStats(month:Int,
                                      day:Int):Flow<Resource<List<AppUsageInfo>>>
    suspend fun getWeeklyAppUsageStats(week:Int):Flow<Resource<List<AppUsageInfo>>>
}