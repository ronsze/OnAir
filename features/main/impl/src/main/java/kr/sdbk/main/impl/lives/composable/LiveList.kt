package kr.sdbk.main.impl.lives.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.sdbk.designsystem.component.image.BaseImage
import kr.sdbk.designsystem.component.spacer.HorizontalSpacer
import kr.sdbk.designsystem.component.spacer.VerticalSpacer
import kr.sdbk.designsystem.component.text.BaseText
import kr.sdbk.designsystem.preview.WhiteBackgroundPreview
import kr.sdbk.domain.model.live.Live
import kr.sdbk.main.impl.R

@Composable
internal fun LiveList(
    lives: List<Live>,
    onSelectLive: (Live) -> Unit,
    onSelectTag: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(lives) { live ->
            LiveListItem(
                live = live,
                onSelect = onSelectLive,
                onSelectTag = onSelectTag
            )
        }
    }
}

@Composable
private fun LiveListItem(
    live: Live,
    onSelect: (Live) -> Unit,
    onSelectTag: (String) -> Unit
) {
    Column(
        modifier = Modifier.clickable { onSelect(live) }
    ) {
        Thumbnail(
            url = live.liveThumbnailImageUrl,
            concurrentUserCount = live.concurrentUserCount
        )
        VerticalSpacer(8.dp)

        ChannelInfo(
            channelImageUrl = live.channelImageUrl,
            liveTitle = live.liveTitle,
            channelName = live.channelName,
            tags = live.tags,
            modifier = Modifier.fillMaxWidth(),
            onSelectTag = onSelectTag
        )
    }
}

@Composable
private fun Thumbnail(
    url: String,
    concurrentUserCount: Int,
    modifier: Modifier = Modifier,
) {
    Box {
        BaseImage(
            url = url,
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Row {
            BaseText(
                text = stringResource(R.string.live_concurrent_user_count_format, concurrentUserCount),
                color = Color.White,
                modifier = Modifier
                    .offset(x = 8.dp, y = 8.dp)
                    .background(Color.Black, RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun ChannelInfo(
    channelImageUrl: String,
    liveTitle: String,
    channelName: String,
    tags: List<String>,
    modifier: Modifier = Modifier,
    onSelectTag: (String) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        BaseImage(
            url = channelImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        HorizontalSpacer(6.dp)

        Column {
            BaseText(
                text = liveTitle,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            VerticalSpacer(6.dp)

            BaseText(
                text = channelName,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
            VerticalSpacer(4.dp)

            TagsRow(
                tags = tags,
                onSelectTag = onSelectTag
            )
        }
    }
}

@Composable
private fun TagsRow(
    tags: List<String>,
    onSelectTag: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { tag ->
            BaseText(
                text = tag,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(6.dp))
                    .clickable { onSelectTag(tag) }
                    .padding(horizontal = 4.dp, vertical = 2.dp)

            )
        }
    }
}

@WhiteBackgroundPreview
@Composable
private fun PreviewItem() {
    LiveListItem(
        live = Live.placeholder.copy(
            liveTitle = "테스트 생방송",
            concurrentUserCount = 10,
            tags = listOf("태그1", "태그2"),
            channelName = "채널1",
            channelImageUrl = ""
        ),
        onSelect = {},
        onSelectTag = {}
    )
}