package kr.sdbk.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.AuthRepositoryImpl
import kr.sdbk.data.repository.ChannelRepositoryImpl
import kr.sdbk.data.repository.LiveRepositoryImpl
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.logic.repository.ChannelRepository
import kr.sdbk.domain.logic.repository.LiveRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsLiveRepository(impl: LiveRepositoryImpl): LiveRepository

    @Binds
    @Singleton
    abstract fun bindsChannelRepository(impl: ChannelRepositoryImpl): ChannelRepository
}