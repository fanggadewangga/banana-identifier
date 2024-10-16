package com.fangga.scan.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText12Regular
import com.fangga.core.utils.noRippleClickable

@Composable
fun CameraMenu(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.noRippleClickable { onClick() }
    ) {
        AppImage(
            imageUrl = icon,
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
        )
        AppText(
            text = title,
            textStyle = bodyText12Regular,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )
    }
}