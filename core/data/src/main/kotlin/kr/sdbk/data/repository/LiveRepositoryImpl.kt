package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.data_source.LiveNetworkDataSource
import kr.sdbk.domain.logic.repository.LiveRepository
import kr.sdbk.domain.model.Live

class LiveRepositoryImpl @Inject constructor(
    private val networkDataSource: LiveNetworkDataSource
) : LiveRepository {
    override suspend fun getLives(size: Int?, next: String?): List<Live> =
        networkDataSource.getLives(size, next)

    override suspend fun getStreamKey(id: Int): String =
        networkDataSource.getStreamKey(id)
}