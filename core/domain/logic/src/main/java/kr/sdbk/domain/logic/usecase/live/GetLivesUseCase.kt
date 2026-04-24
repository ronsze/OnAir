package kr.sdbk.domain.logic.usecase.live

import kr.sdbk.domain.model.Live

interface GetLivesUseCase {
    suspend operator fun invoke(size: Int? = null, next: String? = null): List<Live>
}
