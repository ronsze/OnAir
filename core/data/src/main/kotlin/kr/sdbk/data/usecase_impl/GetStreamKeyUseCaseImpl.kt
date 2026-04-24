package kr.sdbk.data.usecase_impl

import kr.sdbk.domain.logic.repository.LiveRepository
import kr.sdbk.domain.logic.usecase.GetStreamKeyUseCase
import javax.inject.Inject

class GetStreamKeyUseCaseImpl @Inject constructor(
    private val repository: LiveRepository
) : GetStreamKeyUseCase {
    override suspend operator fun invoke(id: Int): String =
        repository.getStreamKey(id)
}
