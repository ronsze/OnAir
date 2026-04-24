package kr.sdbk.domain.logic.usecase.auth

import kr.sdbk.domain.model.User

interface GetUserUseCase {
    operator fun invoke(): User?
}

