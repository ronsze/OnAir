package kr.sdbk.domain.logic.usecase.user_auth

import kr.sdbk.domain.model.user_data.User

interface GetUserUseCase {
    operator fun invoke(): User?
}

