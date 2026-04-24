package kr.sdbk.data.usecase_impl

import kr.sdbk.domain.logic.repository.LiveRepository
import kr.sdbk.domain.logic.usecase.GetLivesUseCase
import kr.sdbk.domain.model.Live
import javax.inject.Inject

class GetLivesUseCaseImpl @Inject constructor(
    private val repository: LiveRepository
) : GetLivesUseCase {
    override suspend operator fun invoke(size: Int?, next: String?): List<Live> =
        repository.getLives(size, next)
}
