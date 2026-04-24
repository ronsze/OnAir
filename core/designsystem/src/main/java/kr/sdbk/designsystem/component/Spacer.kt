package kr.sdbk.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalSpacer(space: Dp) {
    Spacer(Modifier.width(space))
}

@Composable
fun VerticalSpacer(space: Dp) {
    Spacer(Modifier.height(space))
}

@Composable
fun RowScope.WeightSpacer(weight: Float = 1f) {
    Spacer(Modifier.weight(weight))
}

@Composable
fun ColumnScope.WeightSpacer(weight: Float = 1f) {
    Spacer(Modifier.weight(weight))
}