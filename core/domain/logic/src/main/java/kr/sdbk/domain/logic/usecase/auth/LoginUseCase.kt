package kr.sdbk.domain.logic.usecase.auth

import kr.sdbk.domain.model.SocialType

interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String)
    suspend operator fun invoke(token: String, socialType: SocialType)
}