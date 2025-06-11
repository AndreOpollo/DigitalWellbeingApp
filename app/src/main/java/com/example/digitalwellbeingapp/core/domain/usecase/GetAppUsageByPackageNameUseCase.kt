package com.example.digitalwellbeingapp.core.domain.usecase

import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import com.example.digitalwellbeingapp.core.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppUsageByPackageNameUseCase @Inject constructor(
    private val repository: AppUsageRepository
) {
    suspend operator fun invoke(packageName:String,
                                startTime:Long,
                                endTime: Long,
                                interval:Int):Flow<Resource<AppUsageInfo?>>{
        return repository.getAppUsageStatsByPackage(
            packageName = packageName,
            startTime = startTime,
            endTime = endTime,
            interval = interval
            )
    }
}