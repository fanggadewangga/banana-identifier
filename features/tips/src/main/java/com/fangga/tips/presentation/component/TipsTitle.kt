package com.fangga.tips.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.h11SemiBold

@Composable
fun TipsTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        AppText(text = title, textStyle = h11SemiBold)
        HorizontalDivider(
            thickness = (1).dp,
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        )
    }
}