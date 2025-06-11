package com.example.digitalwellbeingapp.core.domain.usecase

import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import com.example.digitalwellbeingapp.core.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetDailyUsageUseCase @Inject constructor(
    private val repository: AppUsageRepository
){
    suspend operator fun invoke(month:Int,day:Int): Flow<Resource<List<AppUsageInfo>>>{
        return repository.getDailyAppUsageStats(month = month,day=day)
    }
}