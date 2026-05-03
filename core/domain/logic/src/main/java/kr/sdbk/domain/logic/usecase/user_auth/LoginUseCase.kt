package kr.sdbk.domain.logic.usecase.user_auth

interface LoginUseCase {
    suspend operator fun invoke(code: String)
}