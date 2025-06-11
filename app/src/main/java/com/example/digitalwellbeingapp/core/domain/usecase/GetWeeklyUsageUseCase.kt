package com.example.digitalwellbeingapp.core.domain.usecase

import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import com.example.digitalwellbeingapp.core.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeeklyUsageUseCase @Inject constructor(
    private val repository: AppUsageRepository
) {
    suspend operator fun invoke(week:Int): Flow<Resource<List<AppUsageInfo>>>{
        return repository.getWeeklyAppUsageStats(week)
    }
}