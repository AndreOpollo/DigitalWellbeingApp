package com.example.digitalwellbeingapp.core.data.repository

import android.system.Os.stat
import com.example.digitalwellbeingapp.core.data.service.UsageStatsManagerSource
import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import com.example.digitalwellbeingapp.core.util.Resource
import com.example.digitalwellbeingapp.core.util.toAppUsageInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppUsageRepositoryImpl @Inject constructor(
    private val usageStatsManagerSource: UsageStatsManagerSource
): AppUsageRepository {
    override suspend fun getAppUsageStatsByPackage(
        packageName: String,
        startTime: Long,
        endTime: Long,
        interval: Int
    ): Flow<Resource<AppUsageInfo?>> = flow {
        emit(Resource.Loading(true))
        try {
            val stats = usageStatsManagerSource.getAppUsageStatsForPackage(
                packageName = packageName,
                startTime = startTime,
                endTime = endTime,
                interval = interval
            )
            val appStats = stats?.toAppUsageInfo()
            emit(Resource.Success(appStats))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.Error(message = e.localizedMessage?:"Something went wrong"))
        }finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getDailyAppUsageStats(
        month: Int,
        day: Int
    ): Flow<Resource<List<AppUsageInfo>>> = flow {
        emit(Resource.Loading(true))
        try {
            val stats = usageStatsManagerSource
                .getDailyStatsUsage(month,day)
            val appStats = stats.map { it.toAppUsageInfo() }
            emit(Resource.Success(appStats))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.Error(message = e.localizedMessage?:"Something went wrong"))
        }finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getWeeklyAppUsageStats(week: Int):
            Flow<Resource<List<AppUsageInfo>>> = flow{
                emit(Resource.Loading(true))
                try {
                    val stats = usageStatsManagerSource.getWeeklyStatsUsage(week)
                    val appStats = stats.map { it.toAppUsageInfo() }
                    emit(Resource.Success(appStats))
                }  catch (e: Exception){
                    e.printStackTrace()
                    emit(Resource.Error(message = e.localizedMessage?:"Something went wrong"))
                }  finally {
                    emit(Resource.Loading(false))
                }

    }
}