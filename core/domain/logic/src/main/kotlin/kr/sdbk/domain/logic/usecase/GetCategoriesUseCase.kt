package kr.sdbk.domain.logic.usecase

import kr.sdbk.domain.model.Category

interface GetCategoriesUseCase {
    suspend operator fun invoke(): List<Category>
}