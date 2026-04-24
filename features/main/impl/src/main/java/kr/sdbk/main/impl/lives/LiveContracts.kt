package kr.sdbk.main.impl.lives

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data object LiveState : State

internal sealed interface LiveIntent : Intent

internal sealed interface LiveEffect : Effect
