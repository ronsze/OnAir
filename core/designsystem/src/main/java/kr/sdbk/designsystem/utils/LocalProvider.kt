package kr.sdbk.designsystem.utils

import androidx.compose.runtime.compositionLocalOf
import coil3.ImageLoader

val LocalImageLoader = compositionLocalOf<ImageLoader> {
    error("ImageLoader not provided")
}