package com.fangga.tips.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.resource.backIcon

@Composable
fun TipsImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageSource: Int,
    contentScale: ContentScale,
    contentDescription: String,
    onBackClick: () -> Unit,
) {
    Box(modifier = modifier) {
        AppImage(
            imageUrl = imageSource,
            contentDescription = contentDescription,
            contentScale = contentScale,
        )
        AppImage(
            imageUrl = backIcon,
            contentDescription = "Back Icon",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .padding(start = 16.dp, top = 32.dp)
                .size(24.dp)
                .clickable { onBackClick() }
        )
    }
}