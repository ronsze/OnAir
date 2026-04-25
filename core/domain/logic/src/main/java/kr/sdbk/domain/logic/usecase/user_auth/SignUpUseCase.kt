package kr.sdbk.domain.logic.usecase.user_auth

interface SignUpUseCase {
    suspend operator fun invoke(email: String, password: String)
}