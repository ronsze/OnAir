package kr.sdbk.data.usecase_impl.auth

import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.logic.usecase.user_auth.GetUserUseCase
import kr.sdbk.domain.model.user_data.User

class GetUserUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : GetUserUseCase {
    override suspend operator fun invoke(forceUpdate: Boolean): User? =
        repository.getCurrentUser(forceUpdate)
}