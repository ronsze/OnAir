package kr.sdbk.data.usecase_impl.live

import kr.sdbk.domain.logic.repository.LiveRepository
import kr.sdbk.domain.logic.usecase.live.GetStreamKeyUseCase
import javax.inject.Inject

class GetStreamKeyUseCaseImpl @Inject constructor(
    private val repository: LiveRepository
) : GetStreamKeyUseCase {
    override suspend operator fun invoke(id: Int): String =
        repository.getStreamKey(id)
}
