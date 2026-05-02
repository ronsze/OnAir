package kr.sdbk.android_extensions.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kr.sdbk.android_extensions.AppConfig
import kr.sdbk.android_extensions.AppConfigImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class EnvironmentModule {
    @Binds
    @Singleton
    abstract fun bindsAppConfig(impl: AppConfigImpl): AppConfig
}