package kr.sdbk.coordinator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S: State, I: Intent, E: Effect>(
    initialState: S
) : ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState = _state.asStateFlow()

    private val _effect: Channel<E> = Channel(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract fun handleIntent(intent: I)

    protected fun updateState(update: S.() -> S) { _state.update { update(it) } }
    protected fun sendEffect(effect: E) { _effect.trySend(effect) }
}