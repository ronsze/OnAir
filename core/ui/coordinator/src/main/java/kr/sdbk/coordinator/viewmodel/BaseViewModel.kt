package kr.sdbk.coordinator.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kr.sdbk.coordinator.model.Error
import kr.sdbk.coordinator.model.LoadingState
import kr.sdbk.coordinator.utils.ErrorMonitor
import kr.sdbk.coordinator.utils.LoadingMonitor

abstract class BaseViewModel<S: State, I: Intent, E: Effect>(
    initialState: S
) : ViewModel() {
    @Inject lateinit var loadingMonitor: LoadingMonitor
    @Inject lateinit var errorMonitor: ErrorMonitor

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState = _state.asStateFlow()

    private val _effect: Channel<E> = Channel(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract fun handleIntent(intent: I)

    protected suspend fun <T, R> T.runTask(
        withLoading: Boolean = true,
        block: suspend T.() -> R
    ): Result<R> {
        if (withLoading) loadingMonitor.update(LoadingState.ON)
        return runCatching { block() }.also {
            if (withLoading) loadingMonitor.update(LoadingState.OFF)
        }.onFailure {
            errorMonitor.update(Error.Default(it.message ?: "알 수 없는 오류"))
        }
    }

    //  종합적인 에러 핸들링에 사용
    protected fun<T> Result<T>.onBasicFailure(
        action: (exception: Throwable) -> Unit
    ): Result<T> = onFailure {
        it.printStackTrace()
        Log.e(this@BaseViewModel::class.java.simpleName, "$it")
        action(it)
    }

    protected fun updateState(update: S.() -> S) { _state.update { update(it) } }
    protected fun sendEffect(effect: E) { _effect.trySend(effect) }
}