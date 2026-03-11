package kr.sdbk.test_config

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.MutableStateFlow
import kr.iamground.common.utils.AppConfig
import kr.iamground.domain.usecase.common_data.GetDateGroupUseCase
import kr.iamground.domain.usecase.user_auth.GetIsLoggedInUseCase
import kr.iamground.domain.usecase.user_data.GetRuntimeUserDataUseCase
import kr.iamground.domain.usecase.user_data.GetUserInfoUseCase
import kr.iamground.domain.usecase.user_data.UpdateDateUseCase
import kr.iamground.domain.usecase.user_data.UpdateSelectedLocationUseCase
import kr.iamground.model_domain.entity.date_time.DateGroup
import kr.iamground.model_domain.entity.user_data.RuntimeUserData
import kr.iamground.model_domain.entity.user_data.UserInfo
import kr.iamground.test_config.usecase.MockUpdateDateUseCase
import kr.iamground.test_config.usecase.MockUpdateSelectedLocationUseCase
import kr.iamground.test_config.utils.MockErrorMonitor
import kr.iamground.test_config.utils.MockNetworkExecutor
import kr.iamground.test_config.utils.MockToastMonitor
import org.junit.Before
import org.junit.Rule

abstract class BaseUnitTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    protected val testDispatcher = mainDispatcherRule.testDispatcher

    protected val mockUserInfo = UserInfo.placeholder
    protected val mockUserInfoFlow: MutableStateFlow<UserInfo?> = MutableStateFlow(mockUserInfo)
    protected val getUserInfoUseCase: GetUserInfoUseCase by lazy { mockk() }

    protected val mockRuntimeUserData = RuntimeUserData.placeholder
    protected val mockRuntimeUserDataFlow = MutableStateFlow(mockRuntimeUserData)
    protected val getRuntimeUserDataUseCase: GetRuntimeUserDataUseCase by lazy { mockk() }

    protected val mockIsLoggedInFlow = MutableStateFlow(true)
    protected val getIsLoggedInUseCase: GetIsLoggedInUseCase by lazy { mockk() }

    protected val mockDateGroup = DateGroup.placeholder
    protected val mockDateGroupFlow = MutableStateFlow(mockDateGroup)
    protected val getDateGroupUseCase: GetDateGroupUseCase = mockk()

    protected val updateDateUseCase: UpdateDateUseCase by lazy { spyk(MockUpdateDateUseCase()) }
    protected val updateSelectedLocationUseCase: UpdateSelectedLocationUseCase by lazy { spyk(MockUpdateSelectedLocationUseCase()) }

    protected val mockToastMonitor = spyk(MockToastMonitor())
    protected val mockErrorMonitor = spyk(MockErrorMonitor())
    protected val mockNetworkExecutor = spyk(MockNetworkExecutor(mockErrorMonitor))

    protected val mockAppConfig = mockk<AppConfig>()

    @Before
    fun baseSetup() {
        coEvery { getUserInfoUseCase() } returns mockUserInfo
        coEvery { getUserInfoUseCase.state(any()) } returns mockUserInfoFlow
        coEvery { getUserInfoUseCase.combined(any()) } returns mockUserInfoFlow

        every { getDateGroupUseCase(any()) } returns mockDateGroup
        every { getDateGroupUseCase.flow(any()) } returns mockDateGroupFlow

        every { getRuntimeUserDataUseCase() } returns mockRuntimeUserDataFlow
        every { getIsLoggedInUseCase() } returns mockIsLoggedInFlow

        every { mockAppConfig.getBaseUrl(any()) } returns ""
        every { mockAppConfig.isDebuggable } returns true
    }
}