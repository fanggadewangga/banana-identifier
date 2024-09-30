package com.fangga.core.components.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.model.enums.ResultItemSwipeType
import com.fangga.core.model.result.ResultList
import com.fangga.core.resource.bodyText12Regular
import com.fangga.core.resource.bodyText14Medium
import com.fangga.core.utils.toDescription

private enum class HorizontalDragValue { Settled, EndToStart }

@Composable
fun ResultItem(
    modifier: Modifier = Modifier,
    data: ResultList,
    swipeType: ResultItemSwipeType,
    onItemClicked: () -> Unit,
    onActionClicked: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Revealed content (e.g., Save button)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C853)) // Green background for "Simpan"
        ) {
            AppText(
                text = "Simpan",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                textStyle = bodyText14Medium,
                color = Color.White
            )
        }

        // Main swipeable content
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        ) {
            AppImage(
                imageUrl = data.image,
                contentDescription = "${data.bananaType.toDescription()} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(height = 88.dp, width = 74.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppText(
                        text = data.bananaType.toDescription(), textStyle = bodyText14Medium
                    )
                    AppText(
                        text = data.ripenessType.toDescription(), textStyle = bodyText12Regular
                    )
                }
                AppText(
                    text = data.timestamp,
                    textStyle = bodyText12Regular,
                    color = Color.LightGray
                )
            }
        }
    }
}
