package kr.sdbk.main.impl.lives

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class LiveViewModel @Inject constructor() : BaseViewModel<LiveState, LiveIntent, LiveEffect>(
    initialState = LiveState
) {
    override fun handleIntent(intent: LiveIntent) {

    }
}
