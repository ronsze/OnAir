package kr.sdbk.onair.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kr.sdbk.designsystem.preview.FullScreenPreview

@Composable
internal fun HandleLoading(
    onLoading: Boolean
) {
    AnimatedVisibility(
        visible = onLoading
    ) {
        LoadingDialog()
    }
}

@Composable
private fun LoadingDialog() {
    Dialog(
        onDismissRequest = {}
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                strokeWidth = 10.dp,
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@FullScreenPreview
@Composable
private fun Preview() {
    LoadingDialog()
}