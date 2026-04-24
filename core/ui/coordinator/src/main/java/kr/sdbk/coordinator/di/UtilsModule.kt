package kr.sdbk.coordinator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.coordinator.utils.ErrorMonitor
import kr.sdbk.coordinator.utils.ErrorMonitorImpl
import kr.sdbk.coordinator.utils.LoadingMonitor
import kr.sdbk.coordinator.utils.LoadingMonitorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UtilsModule {
    @Binds
    @Singleton
    abstract fun bindsLoadingMonitor(impl: LoadingMonitorImpl): LoadingMonitor

    @Binds
    @Singleton
    abstract fun bindsErrorMonitor(impl: ErrorMonitorImpl): ErrorMonitor
}