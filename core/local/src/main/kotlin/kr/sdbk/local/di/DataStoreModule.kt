package kr.sdbk.local.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.datasource.local.TokenLocalDataSource
import kr.sdbk.local.datasource.TokenLocalDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataStoreModule {
    abstract fun bindsAuthDataStore(impl: TokenLocalDataSourceImpl): TokenLocalDataSource
}