package kr.sdbk.coordinator.model

sealed interface Error {
    val message: String

    data class Default(
        override val message: String
    ) : Error

    data class Retryable(
        override val message: String,
        val onClickRetry: () -> Unit
    ) : Error
}