package kr.sdbk.main.impl.my

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data object MyState : State

internal sealed interface MyIntent : Intent

internal sealed interface MyEffect : Effect
