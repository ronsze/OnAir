package kr.sdbk.domain.logic.usecase.user_auth

import kr.sdbk.domain.model.user_data.User

interface GetUserUseCase {
    suspend operator fun invoke(forceUpdate: Boolean): User?
}

