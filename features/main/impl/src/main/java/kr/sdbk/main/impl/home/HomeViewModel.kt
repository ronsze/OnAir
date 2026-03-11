package kr.sdbk.main.impl.home

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class HomeViewModel @Inject constructor() : BaseViewModel<HomeState, HomeIntent, HomeEffect>(
    initialState = HomeState
) {
    override fun handleIntent(intent: HomeIntent) {

    }
}
