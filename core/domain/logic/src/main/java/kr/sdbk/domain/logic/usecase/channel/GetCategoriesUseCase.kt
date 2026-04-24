package kr.sdbk.domain.logic.usecase.channel

import kr.sdbk.domain.model.Category

interface GetCategoriesUseCase {
    suspend operator fun invoke(): List<Category>
}