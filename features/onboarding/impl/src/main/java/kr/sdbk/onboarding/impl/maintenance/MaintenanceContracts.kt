package kr.sdbk.onboarding.impl.maintenance

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data object MaintenanceState : State

internal sealed interface MaintenanceIntent : Intent

internal sealed interface MaintenanceEffect : Effect
