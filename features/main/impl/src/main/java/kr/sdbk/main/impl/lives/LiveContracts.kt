package kr.sdbk.main.impl.lives

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State
import kr.sdbk.domain.model.live.Live

internal data class LiveState(
    val lives: List<Live> = emptyList()
) : State

internal sealed interface LiveIntent : Intent {
    data object UpdatePage : LiveIntent
    data object Refresh : LiveIntent
}

internal sealed interface LiveEffect : Effect
