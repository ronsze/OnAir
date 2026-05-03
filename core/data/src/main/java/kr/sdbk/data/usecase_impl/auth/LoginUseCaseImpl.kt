package kr.sdbk.data.usecase_impl.auth

import android.util.Log
import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.logic.repository.TokenRepository
import kr.sdbk.domain.logic.usecase.user_auth.LoginUseCase

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) : LoginUseCase {
    override suspend operator fun invoke(code: String) {
        val res = authRepository.login(code)
        Log.e("qweqwe", "${res}")
        tokenRepository.setToken(res)
    }
}
