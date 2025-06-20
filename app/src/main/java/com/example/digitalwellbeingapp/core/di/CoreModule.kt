package com.example.digitalwellbeingapp.core.di

import android.app.usage.UsageStatsManager
import android.content.Context
import com.example.digitalwellbeingapp.core.data.repository.AppUsageRepositoryImpl
import com.example.digitalwellbeingapp.core.data.service.UsageStatsManagerSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideUsageStatsManager(@ApplicationContext context: Context): UsageStatsManager{
        return context
            .getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    }

    @Provides
    @Singleton
    fun provideUsageStatsManagerSource(usageStatsManager: UsageStatsManager,
                                       @ApplicationContext context: Context): UsageStatsManagerSource{
        return UsageStatsManagerSource(usageStatsManager, context)
    }

}