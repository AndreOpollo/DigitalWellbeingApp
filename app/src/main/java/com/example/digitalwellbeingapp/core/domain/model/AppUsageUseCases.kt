package com.example.digitalwellbeingapp.core.domain.model

import com.example.digitalwellbeingapp.core.domain.usecase.GetAppUsageByPackageNameUseCase
import com.example.digitalwellbeingapp.core.domain.usecase.GetDailyUsageUseCase
import com.example.digitalwellbeingapp.core.domain.usecase.GetTodayUsageUseCase
import com.example.digitalwellbeingapp.core.domain.usecase.GetWeeklyUsageUseCase

data class AppUsageUseCases(
    val getAppUsageByPackageNameUseCase: GetAppUsageByPackageNameUseCase,
    val getDailyUsageUseCase: GetDailyUsageUseCase,
    val getTodayUsageUseCase: GetTodayUsageUseCase,
    val getWeeklyUsageUseCase: GetWeeklyUsageUseCase
)