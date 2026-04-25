package kr.sdbk.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.datasource.network.ChannelNetworkDataSource
import kr.sdbk.data.datasource.network.LiveNetworkDataSource
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.network.datasource.ChannelNetworkDataSourceImpl
import kr.sdbk.network.datasource.LiveNetworkDataSourceImpl
import kr.sdbk.network.datasource.AuthNetworkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(impl: AuthNetworkDataSourceImpl): AuthNetworkDataSource

    @Binds
    @Singleton
    abstract fun bindsLiveDataSource(impl: LiveNetworkDataSourceImpl): LiveNetworkDataSource

    @Binds
    @Singleton
    abstract fun bindsChannelDataSource(impl: ChannelNetworkDataSourceImpl): ChannelNetworkDataSource
}