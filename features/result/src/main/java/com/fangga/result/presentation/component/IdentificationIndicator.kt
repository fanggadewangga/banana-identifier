package com.fangga.result.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.h12SemiBold
import com.fangga.core.resource.lightGreen

@Composable
fun IdentificationIndicator(
    modifier: Modifier = Modifier,
    indicator: String,
    value: String,
    @DrawableRes icon: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(color = lightGreen, shape = RoundedCornerShape(8.dp))
        ) {
            AppImage(
                imageUrl = icon,
                contentDescription = "Indicator icon",
                modifier = Modifier.size(24.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AppText(text = indicator, textStyle = bodyText14Regular)
            AppText(text = value, textStyle = h12SemiBold)
        }
    }
}