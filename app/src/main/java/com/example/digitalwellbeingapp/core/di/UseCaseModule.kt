package com.example.digitalwellbeingapp.core.di

import com.example.digitalwellbeingapp.core.domain.model.AppUsageUseCases
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import com.example.digitalwellbeingapp.core.domain.usecase.GetAppUsageByPackageNameUseCase
import com.example.digitalwellbeingapp.core.domain.usecase.GetDailyUsageUseCase
import com.example.digitalwellbeingapp.core.domain.usecase.GetTodayUsageUseCase
import com.example.digitalwellbeingapp.core.domain.usecase.GetWeeklyUsageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun getAppUsageUseCases(
        appUsageRepository: AppUsageRepository
    ): AppUsageUseCases{
        return AppUsageUseCases(
            getAppUsageByPackageNameUseCase = GetAppUsageByPackageNameUseCase(appUsageRepository),
            getDailyUsageUseCase = GetDailyUsageUseCase(appUsageRepository),
            getTodayUsageUseCase = GetTodayUsageUseCase(appUsageRepository),
            getWeeklyUsageUseCase = GetWeeklyUsageUseCase(appUsageRepository)
        )
    }
}