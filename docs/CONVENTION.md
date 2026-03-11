# OnAir Project Conventions

This document defines the coding conventions and naming standards for the OnAir Android project.

## Table of Contents

1. [Navigation Conventions](#navigation-conventions)
2. [Screen Architecture](#screen-architecture)
3. [Naming Conventions](#naming-conventions)

---

## Navigation Conventions

### Back Navigation

When implementing a back button or back navigation functionality:

- **UI Event (Screen)** : Use `onBackPressed` as the function name in `UiEvents`
  ```kotlin
  internal data class ExampleUiEvents(
      val onBackPressed: () -> Unit
  )
  ```

- **Events (Callbacks)** : Use `goBack` as the event name in `Events` data class
  ```kotlin
  internal data class ExampleEvents(
      val goBack: () -> Unit
  )
  ```

- **Binding** : In Navigator, bind `navigator::goBack` to `events.goBack`
  ```kotlin
  val events = remember {
      ExampleEvents(
          goBack = navigator::goBack
      )
  }
  ```

- **Route** : The route name follows `{ScreenName}Route` pattern
  ```kotlin
  @Serializable
  data object ExampleRoute : ModuleNavKey
  ```

- **Navigation Function** : Extension function on `OnAirNavigator`
  ```kotlin
  fun OnAirNavigator.navigateToExample() = navigate(ExampleRoute)
  ```

---

## Screen Architecture

### MVI Pattern

Each screen follows the MVI (Model-View-Intent) pattern with three core components:

1. **State** : Immutable state representation
2. **Intent** : User actions that trigger state changes
3. **Effect** : One-time side effects (navigation, toasts, etc.)

### File Structure

Each screen consists of 3 files:

```
{ScreenName}/
├── {ScreenName}Screen.kt      // UI Layer
├── {ScreenName}Contracts.kt   // State/Intent/Effect definitions
└── {ScreenName}ViewModel.kt   // Business Logic
```

### Screen.kt Structure

```kotlin
@Composable
internal fun ExampleScreen(
    events: ExampleEvents,
    viewModel: ExampleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember {
        ExampleUiEvents(
            // Direct access for pure navigation
            onBackPressed = events.goBack,
            // Via ViewModel for data operations
            onSubmit = { viewModel.handleIntent(ExampleIntent.Submit(it)) }
        )
    }

    ExampleView(uiState, uiEvents)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ExampleEffect.NavigateToNext -> events.navigateToNext()
            }
        }
    }
}
```

---

## Naming Conventions

### General Rules

| Component | Naming Pattern | Example |
|-----------|----------------|---------|
| Screen Composable | `{Name}Screen` | `SplashScreen` |
| View Composable | `{Name}View` | `SplashView` |
| Events (nav callbacks) | `{Name}Events` | `SplashEvents` |
| UI Events (user actions) | `{Name}UiEvents` | `SplashUiEvents` |
| State | `{Name}State` | `SplashState` |
| Intent | `{Name}Intent` | `SplashIntent` |
| Effect | `{Name}Effect` | `SplashEffect` |
| ViewModel | `{Name}ViewModel` | `SplashViewModel` |
| Route | `{Name}Route` | `SplashRoute` |

### Event Naming by Type

| Event Type | Naming Pattern | Example |
|------------|----------------|---------|
| Back/Navigate back | `onBackPressed` / `goBack` | `onBackPressed` (UiEvents), `goBack` (Events) |
| Navigate to X | `navigateTo{X}` | `navigateToHome`, `navigateToSettings` |
| Button click | `on{Action}Click` | `onSubmitClick`, `onRetryClick` |
| Text change | `on{Field}Change` | `onEmailChange`, `onPasswordChange` |
| Toggle/Check | `on{Item}Toggle` / `on{Item}Check` | `onNotificationToggle` |
| Submit/Confirm | `on{Action}` | `onSubmit`, `onConfirm` |

### Module Navigation

| Component | Naming Pattern | Example |
|-----------|----------------|---------|
| Module NavKey Interface | `{Module}NavKey` | `OnboardingNavKey` |
| Navigation Extension | `navigateTo{Screen}` | `navigateToSplash()` |
| Navigator Extension | `{module}Navigator` | `onboardingNavigator` |

---

## Package Structure

```
features/{feature}/
├── api/
│   └── java/kr/sdbk/{feature}/api/
│       └── {Feature}NavKey.kt          # Routes and navigation functions
└── impl/
    └── java/kr/sdbk/{feature}/impl/
        ├── {Feature}Navigator.kt        # Entry provider scope extension
        └── {screen}/
            ├── {Screen}Screen.kt
            ├── {Screen}Contracts.kt
            └── {Screen}ViewModel.kt
```

---

## Modifier Visibility

All screen-level components use `internal` modifier:

```kotlin
internal data class ExampleEvents(...)
internal data class ExampleUiEvents(...)
internal data object ExampleState : State

@Composable
internal fun ExampleScreen(...) { }
```
