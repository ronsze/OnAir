package kr.sdbk.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.network.datasource.FirebaseAuthNetworkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(impl: FirebaseAuthNetworkDataSourceImpl): AuthNetworkDataSource
}