package kr.sdbk.data.usecase_impl.auth

import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.logic.usecase.user_auth.SignUpUseCase

class SignUpUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignUpUseCase {
    override suspend operator fun invoke(email: String, password: String) {
        repository.signUpWithEmail(email, password)
    }
}