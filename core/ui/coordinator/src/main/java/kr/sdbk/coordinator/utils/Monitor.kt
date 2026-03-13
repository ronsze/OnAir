package kr.sdbk.coordinator.utils

import kotlinx.coroutines.flow.Flow

interface Monitor <T> {
    val data: Flow<T>
    fun update(data: T)
}