package kr.sdbk.domain.logic.usecase.user_auth

import kr.sdbk.domain.model.user_auth.AuthToken

interface LoginUseCase {
    suspend operator fun invoke(): AuthToken
}