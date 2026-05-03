package kr.sdbk.main.impl.lives

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kr.sdbk.coordinator.viewmodel.BaseViewModel
import kr.sdbk.domain.logic.usecase.live.GetLivesUseCase

@HiltViewModel
internal class LiveViewModel @Inject constructor(
    private val getLivesUseCase: GetLivesUseCase
) : BaseViewModel<LiveState, LiveIntent, LiveEffect>(
    initialState = LiveState()
) {
    private var nextKey: String? = null

    override suspend fun onInitialized() {
        refresh()
    }

    override fun handleIntent(intent: LiveIntent) {
        when (intent) {
            is LiveIntent.UpdatePage -> loadData()
            is LiveIntent.Refresh -> refresh()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            runTask {
                getLivesUseCase(next = nextKey)
            }.onSuccess { res ->
                val newList = state.value.lives + res.data
                updateState { copy(lives = newList) }
                nextKey = res.page.next
            }.onBasicFailure {}
        }
    }

    private fun refresh() {
        updateState { copy(lives = emptyList()) }
        loadData()
    }
}