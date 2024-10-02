package com.fangga.tips.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.components.feature.PointItem
import com.fangga.core.components.feature.ScanHelper
import com.fangga.core.resource.bodyText12Regular
import com.fangga.core.resource.h11SemiBold
import com.fangga.core.resource.scanHelperHeaderIcon
import com.fangga.tips.data.Static
import com.fangga.tips.domain.model.TipsStep

@Composable
fun TipsDetailContent(
    modifier: Modifier = Modifier,
    tipsId: String,
    @DrawableRes imageSource: Int,
    title: String,
    description: @Composable () -> Unit,
    steps: List<TipsStep>,
    onBackClick: () -> Unit,
) {
    LazyColumn(modifier = modifier) {
        item {
            TipsImage(
                imageSource = imageSource,
                contentScale = ContentScale.Crop,
                contentDescription = "$title image",
                onBackClick = { onBackClick() },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            TipsTitle(
                title = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        item {
            description()
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(steps) {
            PointItem(
                number = it.number.toString(),
                title = it.title,
                content = {
                    AppText(text = it.description, textStyle = bodyText12Regular)
                },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
            )
        }

        if (tipsId == Static.tipsDetail.first().tipsId) {
            item {
                ScanHelper(
                    helperHeader = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AppImage(
                                imageUrl = scanHelperHeaderIcon, 
                                contentDescription = "Warning Icon",
                                modifier = Modifier.size(20.dp)
                            )
                            AppText(text = "Bantuan Untuk Scan Pisang", textStyle = h11SemiBold)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}