package kr.sdbk.data.usecase_impl.live

import kr.sdbk.domain.logic.repository.LiveRepository
import kr.sdbk.domain.logic.usecase.live.GetLivesUseCase
import kr.sdbk.domain.model.live.LivesResponse
import javax.inject.Inject

class GetLivesUseCaseImpl @Inject constructor(
    private val repository: LiveRepository
) : GetLivesUseCase {
    override suspend operator fun invoke(size: Int?, next: String?): LivesResponse =
        repository.getLives(size, next)
}
