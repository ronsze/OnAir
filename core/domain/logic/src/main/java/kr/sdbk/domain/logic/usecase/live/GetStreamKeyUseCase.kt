package kr.sdbk.domain.logic.usecase.live

interface GetStreamKeyUseCase {
    suspend operator fun invoke(id: Int): String
}
