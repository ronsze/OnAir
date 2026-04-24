package kr.sdbk.main.impl.following

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class FollowingViewModel @Inject constructor() : BaseViewModel<FollowingState, FollowingIntent, FollowingEffect>(
    initialState = FollowingState
) {
    override fun handleIntent(intent: FollowingIntent) {

    }
}
