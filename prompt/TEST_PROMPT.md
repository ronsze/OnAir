# AI Test Writing Protocol

> INSTRUCTION: When writing ViewModel tests, follow these rules exactly. Do not deviate.

---

## MANDATORY RULES

### 1. Test Case Source Rule
- Test cases are defined by existing `@Test` function names in the test file
- When user says "테스트 작성", write test implementations for ALL empty/failing tests based on their function names
- Function names use Korean + backticks format describing the test scenario
- DO NOT add scenario comments - the function name IS the scenario

```kotlin
@Test
fun `로그인 상태 변경시 유저 정보를 불러온다`() = runTest { }
```

### 2. Merge Related Tests
- If test functions share: same setup, similar mocks, or related logic → **MERGE into single test**
- Example: "로그인 성공시" + "유저 정보 로드 성공시" = one merged test

### 3. Data Declaration Pattern
- ALWAYS use `placeholder.copy()`
- Declare as class member variables (top of test class)
- Naming: `mock{Entity}{Condition}`

```kotlin
private val mockUserInfoWithTeam = UserInfo.placeholder.copy(
    name = "홍길동",
    email = "hong@example.com",
    teamMatchInfo = UserTeamInfo.placeholder.copy(
        teamRating = 1500f,
        teamGrade = Level.A,
        good = 10,
        bad = 2
    )
)

private val mockUserInfoNull = UserInfo.placeholder.copy(teamMatchInfo = null)
```

### 4. NO Comments in Test Functions
- ❌ NO `// Given`, `// When`, `// Then`
- ❌ NO explanatory comments inside test functions
- ❌ NO `// scenario` comments
- Function name is sufficient documentation

### 5. Required Annotations & Inheritance
```kotlin
@RunWith(RobolectricTestRunner::class)
class XxxViewModelTest : BaseUnitTest() {
    // ...
}
```

### 6. Context Creation Rule
When ViewModel requires `@ApplicationContext`:
- **NEVER** use `mockk<Context>()` or `mockk(relaxed = true)`
- **ALWAYS** use `spyk(ApplicationProvider.getApplicationContext())`

```kotlin
import androidx.test.core.app.ApplicationProvider
import io.mockk.spyk

private val mockContext: Context = spyk(ApplicationProvider.getApplicationContext())
```

**Why spyk instead of mockk?**
- `ApplicationProvider.getApplicationContext()` provides real Android context
- `spyk()` wraps real instance while allowing method stubbing
- `mockk()` creates pure mock without real context behavior

### 7. StaticMocking Rule
For global/extension function mocking, use **StaticMocking** utilities:

```kotlin
import kr.sdbk.test_config.utils.mockkCookieManager
import kr.sdbk.test_config.utils.mockkCurrentVersion
import kr.sdbk.test_config.utils.unMockkCurrentVersion

@Before
fun setup() {
    mockkCookieManager()
    mockkCurrentVersion("1.0.0")
    // ...
}

@After
fun tearDown() {
    unMockkCurrentVersion()
}
```

**Available StaticMocking functions:**
- `mockkCookieManager()` - Mocks CookieManager.getInstance()
- `mockkCurrentVersion(version)` - Mocks getCurrentAppVersion()
- `unMockkCurrentVersion()` - Cleanup for version mock
- `mockkLogBeginCheckout()` - Mocks purchase analytics
- `mockkResetAnalyticsUserData()` - Mocks analytics reset
- `mockkSetAnalyticsUserProperties()` - Mocks analytics properties
- `mockkLogSearch()` - Mocks search analytics
- `mockkCurrentTime(time)` - Mocks getCurrentTime()
- `unMockkCurrentTime()` - Cleanup for time mock

**❌ NEVER manually mock statics:**
```kotlin
// Wrong way
mockkStatic(CookieManager::class)
every { CookieManager.getInstance() } returns mockk()
```

**✅ ALWAYS use StaticMocking:**
```kotlin
// Correct way
mockkCookieManager()
```

### 8. ViewModel Instance Creation Rule
When writing tests, **ALWAYS** extract ViewModel instantiation into a separate private function:

```kotlin
private fun createViewModel(navKey: {NavKey}): {ViewModel} {
    return {ViewModel}(
        ioDispatcher = mainDispatcherRule.testDispatcher,
        useCase = useCase,
        navKey = navKey
    ).apply {
        networkExecutor = mockNetworkExecutor
        errorMonitor = mockErrorMonitor
    }
}
```

**Why?**
- Keeps test functions focused on the actual test logic
- Allows easy parameter customization per test
- Prevents duplication of setup code
- Makes tests more maintainable when ViewModel constructor changes

**❌ NEVER instantiate ViewModel directly inside test functions:**
```kotlin
// Wrong way
@Test
fun `테스트`() = runTest {
    val viewModel = ViewModel(
        ioDispatcher = mainDispatcherRule.testDispatcher,
        useCase = useCase
    ).apply {
        networkExecutor = mockNetworkExecutor
        errorMonitor = mockErrorMonitor
    }
}
```

**✅ ALWAYS use createViewModel function:**
```kotlin
// Correct way
@Test
fun `테스트`() = runTest {
    viewModel = createViewModel(mockNavKey)
    // ... test logic
}
```

### 9. Test Ordering Rule
Order test functions by ViewModel's code flow to improve readability and maintainability:

```kotlin
class XxxViewModelTest : BaseUnitTest() {

    // 1. Initialization & State Collection Tests
    @Test
    fun `state 구독시 초기 데이터를 로드한다`() = runTest { }

    @Test
    fun `Handler의 상태 변화가 ViewModel State에 반영된다`() = runTest { }

    // 2. Handler Effect Propagation Tests
    @Test
    fun `Handler의 Effect가 ViewModel Effect로 전달된다`() = runTest { }

    // 3. Intent Handling Tests - by usage frequency/importance
    @Test
    fun `Refresh Intent시 데이터를 다시 로드한다`() = runTest { }

    @Test
    fun `로그인 상태 변경시 데이터를 다시 로드한다`() = runTest { }

    // 4. Dialog/Effect Tests
    @Test
    fun `업데이트 다이얼로그 표시 조건을 확인한다`() = runTest { }

    @Test
    fun `다이얼로그 닫기 Intent시 상태가 업데이트된다`() = runTest { }
}
```

**Ordering Guidelines:**
1. **State collection** tests first (Flow subscription, initial data loading)
2. **Handler integration** tests (State/Effect propagation from handlers)
3. **Intent handling** tests by logical flow (Initialize → Refresh → User actions)
4. **Edge cases** last (Error handling, dialog management)

**Why?**
- Easier to correlate tests with ViewModel source code
- Groups related functionality together
- Simplifies identifying missing coverage areas

---

## TEST COVERAGE WORKFLOW WITH KOVER

After writing initial tests, follow this workflow to ensure adequate coverage:

### Step 1: Generate Kover Report
```bash
./gradlew :{module}:koverHtmlReport
```

### Step 2: Check Coverage Metrics
Review the generated HTML report at:
```
{module}/build/reports/kover/html/index.html
```

Focus on these metrics (in priority order):
1. **Class coverage** - Target: 100%
2. **Method coverage** - Target: 100%
3. **Line coverage** - Target: 90%+
4. **Branch coverage** - Target: 70%+ (100% NOT required)

### Step 3: Identify Missing Coverage
Look for:
- ❌ Red lines in source code view (uncovered lines)
- ❌ Yellow branch indicators (partially covered branches)
- ❌ Methods with 0% coverage

### Step 4: Add Missing Test Cases
Add tests for uncovered branches:

```kotlin
@Test
fun `로그인 상태가 로그인으로 변경시 로컬 유저 데이터를 초기화하지 않는다`() = runTest {
    // Test the opposite branch condition
}
```

### Step 5: Re-run Kover
Repeat until Class/Method/Line coverage targets are met.

---

## OBSERVE FLOW PATTERN

When testing `observe()` extension function (StateFlow with `WhileSubscribed`):

```kotlin
@Test
fun `로그인 상태 변경시 유저 정보를 불러온다`() = runTest {
    // 1. Trigger state change
    mockIsLoggedInFlow.update { false }
    
    // 2. Subscribe to StateFlows (order matters)
    viewModel.uiState.test {
        cancelAndIgnoreRemainingEvents()
    }
    
    // 3. Can subscribe separately
    viewModel.isLoggedIn.test { cancelAndIgnoreRemainingEvents() }
    
    // 4. Verify UseCase invocation
    coVerify { useCase(any()) }
}
```

**Key Points:**
- `stateIn(WhileSubscribed)` Flows require subscription to activate
- Can subscribe separately or nested
- State change can happen before subscription
- Always use `cancelAndIgnoreRemainingEvents()` to cleanup

---

## VERIFICATION PRIORITY

When writing assertions, prefer in this order:

1. **UseCase invocation**: `coVerify { useCase(any()) }` — most stable
2. **State value**: `assertEquals(expected, actual)`
3. **Type check**: `assertTrue(state is ExpectedType)`
4. **Null check**: `assertNull(value)` / `assertNotNull(value)`
5. **Effect emission**: `effect.awaitItem()`

---

## TURBINE PATTERNS

```kotlin
// State verification
viewModel.uiState.test {
    val state = expectMostRecentItem()
    assertEquals(expected, state.field)
}

// Effect verification  
viewModel.effect.test {
    viewModel.handleIntent(Intent.Action)
    val effect = awaitItem()
    assertTrue(effect is ExpectedEffect)
}

// Required StateFlow subscription cleanup
viewModel.isLoggedIn.test { cancelAndIgnoreRemainingEvents() }
```

---

## CHECKLIST (Validate After Writing)

- [ ] Test function names use Korean + backticks (describes the scenario)
- [ ] NO comments inside test functions (function name is documentation)
- [ ] All test data uses `placeholder.copy()`
- [ ] Data declared as class member variables
- [ ] Extends `BaseUnitTest`
- [ ] Has `@RunWith(RobolectricTestRunner::class)`
- [ ] Uses `mainDispatcherRule.testDispatcher`
- [ ] Sets `networkExecutor = mockNetworkExecutor`
- [ ] Sets `errorMonitor = mockErrorMonitor`
- [ ] Calls `cancelAndIgnoreRemainingEvents()` for StateFlow subscriptions
- [ ] **ViewModel instantiated via `createViewModel()` function**
- [ ] **Kover coverage checked (Class/Method/Line targets met)**
