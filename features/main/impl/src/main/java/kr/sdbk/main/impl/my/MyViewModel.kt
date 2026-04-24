package kr.sdbk.main.impl.my

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class MyViewModel @Inject constructor() : BaseViewModel<MyState, MyIntent, MyEffect>(
    initialState = MyState
) {
    override fun handleIntent(intent: MyIntent) {

    }
}
