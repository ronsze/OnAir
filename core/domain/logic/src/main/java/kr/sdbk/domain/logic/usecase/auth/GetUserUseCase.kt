package kr.sdbk.domain.logic.usecase.auth

import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.model.User

interface GetUserUseCase {
    operator fun invoke(): User?
}

class GetUserUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : GetUserUseCase {
    override operator fun invoke(): User? = repository.getCurrentUser()
}
