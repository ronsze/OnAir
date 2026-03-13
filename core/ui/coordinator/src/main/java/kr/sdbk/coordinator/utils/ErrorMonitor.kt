package kr.sdbk.coordinator.utils

import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kr.sdbk.coordinator.model.Error

interface ErrorMonitor : Monitor<Error?>

internal class ErrorMonitorImpl @Inject constructor() : ErrorMonitor {
    private val error: Channel<Error?> = Channel(Channel.BUFFERED)
    override val data: Flow<Error?> = error.receiveAsFlow()

    override fun update(data: Error?) {
        error.trySend(data)
    }
}