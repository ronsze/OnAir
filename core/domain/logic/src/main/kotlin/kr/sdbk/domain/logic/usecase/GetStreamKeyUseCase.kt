package kr.sdbk.domain.logic.usecase

interface GetStreamKeyUseCase {
    suspend operator fun invoke(id: Int): String
}
