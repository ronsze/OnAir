package kr.sdbk.domain.logic.usecase.auth

interface SignUpUseCase {
    suspend operator fun invoke(email: String, password: String)
}