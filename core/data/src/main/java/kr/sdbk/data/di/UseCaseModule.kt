package kr.sdbk.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kr.sdbk.domain.logic.usecase.auth.GetUserUseCase
import kr.sdbk.domain.logic.usecase.auth.GetUserUseCaseImpl
import kr.sdbk.domain.logic.usecase.auth.LoginUseCase
import kr.sdbk.domain.logic.usecase.auth.LoginUseCaseImpl
import kr.sdbk.domain.logic.usecase.auth.SignUpUseCase
import kr.sdbk.domain.logic.usecase.auth.SignUpUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
    @Binds
    abstract fun bindGetUserUseCase(impl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
}
