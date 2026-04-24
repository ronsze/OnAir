package kr.sdbk.network.api

import kr.sdbk.network.model.CategoryDTO
import retrofit2.http.GET

interface ChannelAPI {
    @GET("/open/v1/categories")
    suspend fun getCategories(): List<CategoryDTO>
}