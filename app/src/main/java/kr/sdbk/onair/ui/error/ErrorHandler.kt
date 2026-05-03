package kr.sdbk.onair.ui.error

import androidx.compose.runtime.Composable
import kr.sdbk.coordinator.model.Error

@Composable
internal fun HandleError(
    error: Error?,
    dismissError: () -> Unit
) {
    when (error) {
        is Error.Default -> {
            DefaultErrorDialog(
                message = error.message,
                onDismissRequest = dismissError
            )
        }
        is Error.Retryable -> {
            RetryableErrorDialog(
                message = error.message,
                onClickRetry = error.onClickRetry,
                onDismissRequest = dismissError
            )
        }
        else -> Unit
    }
}