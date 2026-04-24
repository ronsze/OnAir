package kr.sdbk.main.impl.following

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data object FollowingState : State

internal sealed interface FollowingIntent : Intent

internal sealed interface FollowingEffect : Effect
