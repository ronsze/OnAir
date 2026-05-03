package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.LiveNetworkDataSource
import kr.sdbk.domain.logic.repository.LiveRepository

class LiveRepositoryImpl @Inject constructor(
    private val networkDataSource: LiveNetworkDataSource
) : LiveRepository {
    override suspend fun getLives(size: Int?, next: String?) =
        networkDataSource.getLives(size, next)

    override suspend fun getStreamKey(id: Int): String =
        networkDataSource.getStreamKey(id)
}