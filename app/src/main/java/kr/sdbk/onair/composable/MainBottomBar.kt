package kr.sdbk.onair.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import kr.sdbk.designsystem.R
import kr.sdbk.designsystem.component.text.BaseText
import kr.sdbk.designsystem.theme.OnAirTheme
import kr.sdbk.main.api.TopLevelDestinations

@Composable
internal fun MainBottomBar(
    currentDestination: TopLevelDestinations = TopLevelDestinations.HomeNavKey,
    onSelectTab: (TopLevelDestinations) -> Unit = {}
) {
    NavigationBar {
        TopLevelDestinations.entries.forEach { destination ->
            Item(
                isSelected = destination == currentDestination,
                icon = destination.icon(),
                label = destination.label(),
                onClick = { onSelectTab(destination) }
            )
        }
    }
}

@Composable
private fun RowScope.Item(
    isSelected: Boolean,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = isSelected,
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
            )
        },
        label = {
            BaseText(
                text = stringResource(label),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        },
        onClick = onClick
    )
}

private fun TopLevelDestinations.icon() = when (this) {
    TopLevelDestinations.HomeNavKey -> R.drawable.ic_home
    TopLevelDestinations.FollowingNavKey -> R.drawable.ic_following
    TopLevelDestinations.LivesNavKey -> R.drawable.ic_live
    TopLevelDestinations.MyNavKey -> R.drawable.ic_my
}

private fun TopLevelDestinations.label() = when (this) {
    TopLevelDestinations.HomeNavKey -> R.string.home
    TopLevelDestinations.FollowingNavKey -> R.string.following
    TopLevelDestinations.LivesNavKey -> R.string.lives
    TopLevelDestinations.MyNavKey -> R.string.my
}

private class TopLevelDestinationsPreviewParameterProvider : PreviewParameterProvider<TopLevelDestinations> {
    override val values: Sequence<TopLevelDestinations> = TopLevelDestinations.entries.asSequence()
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview(
    @PreviewParameter(TopLevelDestinationsPreviewParameterProvider::class)
    selectedDestination: TopLevelDestinations
) {
    OnAirTheme {
        MainBottomBar(currentDestination = selectedDestination)
    }
}
