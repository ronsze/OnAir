package kr.sdbk.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kr.sdbk.data.usecase_impl.auth.GetUserUseCaseImpl
import kr.sdbk.data.usecase_impl.auth.LoginUseCaseImpl
import kr.sdbk.data.usecase_impl.auth.SignUpUseCaseImpl
import kr.sdbk.data.usecase_impl.channel.GetCategoriesUseCaseImpl
import kr.sdbk.data.usecase_impl.live.GetLivesUseCaseImpl
import kr.sdbk.data.usecase_impl.live.GetStreamKeyUseCaseImpl
import kr.sdbk.domain.logic.usecase.auth.GetUserUseCase
import kr.sdbk.domain.logic.usecase.auth.LoginUseCase
import kr.sdbk.domain.logic.usecase.auth.SignUpUseCase
import kr.sdbk.domain.logic.usecase.channel.GetCategoriesUseCase
import kr.sdbk.domain.logic.usecase.live.GetLivesUseCase
import kr.sdbk.domain.logic.usecase.live.GetStreamKeyUseCase

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
    @Binds abstract fun bindGetUserUseCase(impl: GetUserUseCaseImpl): GetUserUseCase
    @Binds abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase
    @Binds abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
    @Binds abstract fun bindGetCategoriesUseCase(impl: GetCategoriesUseCaseImpl): GetCategoriesUseCase
    @Binds abstract fun bindGetLivesUseCase(impl: GetLivesUseCaseImpl): GetLivesUseCase
    @Binds abstract fun bindGetStreamKeyUseCase(impl: GetStreamKeyUseCaseImpl): GetStreamKeyUseCase
}
