package kr.sdbk.data.usecase_impl.channel

import jakarta.inject.Inject
import kr.sdbk.domain.logic.repository.ChannelRepository
import kr.sdbk.domain.logic.usecase.channel.GetCategoriesUseCase
import kr.sdbk.domain.model.channel.Category

class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: ChannelRepository
): GetCategoriesUseCase {
    override suspend operator fun invoke(): List<Category> =
        repository.getCategories()
}