package com.example.digitalwellbeingapp.core.domain.usecase

import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import com.example.digitalwellbeingapp.core.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class GetTodayUsageUseCase @Inject constructor(
    private val repository: AppUsageRepository
){
    suspend operator fun invoke():Flow<Resource<List<AppUsageInfo>>>{
        val calendar = Calendar.getInstance()
        val todayMonth = calendar.get(Calendar.MONTH)
        val todayDay = calendar.get(Calendar.DAY_OF_MONTH)
        return repository.getDailyAppUsageStats(month = todayMonth, day = todayDay)
    }
}