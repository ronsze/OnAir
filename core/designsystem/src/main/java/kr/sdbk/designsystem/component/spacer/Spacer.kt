package kr.sdbk.designsystem.component.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalSpacer(size: Dp) {
    Spacer(Modifier.width(size))
}

@Composable
fun VerticalSpacer(size: Dp) {
    Spacer(Modifier.height(size))
}

@Composable
fun RowScope.WeightSpacer(weight: Float) {
    Spacer(Modifier.weight(weight))
}

@Composable
fun ColumnScope.WeightSpacer(weight: Float) {
    Spacer(Modifier.weight(weight))
}
