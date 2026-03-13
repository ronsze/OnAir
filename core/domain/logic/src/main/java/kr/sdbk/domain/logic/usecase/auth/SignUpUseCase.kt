package kr.sdbk.domain.logic.usecase.auth

import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.AuthRepository

interface SignUpUseCase {
    suspend operator fun invoke(email: String, password: String)
}

class SignUpUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignUpUseCase {
    override suspend operator fun invoke(email: String, password: String) {
        repository.signUpWithEmail(email, password)
    }
}
