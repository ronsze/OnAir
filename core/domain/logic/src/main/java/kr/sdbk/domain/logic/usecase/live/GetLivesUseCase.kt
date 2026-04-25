package kr.sdbk.domain.logic.usecase.live

import kr.sdbk.domain.model.live.LivesResponse

interface GetLivesUseCase {
    suspend operator fun invoke(size: Int? = null, next: String? = null): LivesResponse
}
