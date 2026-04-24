package kr.sdbk.data.usecase_impl.auth

import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.logic.usecase.auth.LoginUseCase
import kr.sdbk.domain.model.SocialType

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LoginUseCase {
    override suspend operator fun invoke(email: String, password: String) =
        authRepository.loginWithEmail(email, password)

    override suspend operator fun invoke(token: String, socialType: SocialType) =
        authRepository.loginWithSocial(token, socialType)
}
