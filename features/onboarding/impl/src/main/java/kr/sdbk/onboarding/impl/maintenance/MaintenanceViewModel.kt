package kr.sdbk.onboarding.impl.maintenance

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class MaintenanceViewModel @Inject constructor() : BaseViewModel<MaintenanceState, MaintenanceIntent, MaintenanceEffect>(
    initialState = MaintenanceState
) {
    override fun handleIntent(intent: MaintenanceIntent) {

    }
}
