package kr.sdbk.main.impl.home

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data object HomeState : State

internal sealed interface HomeIntent : Intent

internal sealed interface HomeEffect : Effect
