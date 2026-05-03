package kr.sdbk.designsystem.component.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import coil3.ColorImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State
import coil3.request.ImageRequest
import kr.sdbk.designsystem.utils.LocalImageLoader

@Composable
fun BaseImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = error,
    onLoading: ((State.Loading) -> Unit)? = null,
    onSuccess: ((State.Success) -> Unit)? = null,
    onError: ((State.Error) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    clipToBounds: Boolean = true,
) {
    val isPreview = LocalInspectionMode.current

    if (isPreview) {
        Image(
            painter = ColorPainter(Color.Gray),
            contentDescription = null,
            modifier = modifier
        )
    } else {
        val context = LocalContext.current
        val imageLoader = LocalImageLoader.current
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        AsyncImage(
            model = request,
            modifier = modifier,
            contentDescription = contentDescription,
            placeholder = placeholder,
            imageLoader = imageLoader,
            error = error,
            fallback = fallback,
            onLoading = onLoading,
            onSuccess = onSuccess,
            onError = onError,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            filterQuality = filterQuality,
            clipToBounds = clipToBounds,
        )
    }
}