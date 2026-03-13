package kr.sdbk.coordinator.utils

import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kr.sdbk.coordinator.model.LoadingState

interface LoadingMonitor : Monitor<LoadingState>

internal class LoadingMonitorImpl @Inject constructor() : LoadingMonitor {
    private val _loadingState: Channel<LoadingState> = Channel(Channel.BUFFERED)
    override val data: Flow<LoadingState> = _loadingState.receiveAsFlow()

    override fun update(data: LoadingState) {
        _loadingState.trySend(data)
    }
}