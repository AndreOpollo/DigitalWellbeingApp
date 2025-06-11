package com.example.digitalwellbeingapp.core.di

import com.example.digitalwellbeingapp.core.data.repository.AppUsageRepositoryImpl
import com.example.digitalwellbeingapp.core.domain.repository.AppUsageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAppUsageRepository(
        appUsageRepositoryImpl: AppUsageRepositoryImpl
    ): AppUsageRepository
}