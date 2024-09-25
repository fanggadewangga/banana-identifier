package com.fangga.features.onboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.h10SemiBold
import com.fangga.features.onboard.domain.OnboardItem

@Composable
fun OnboardItem(
    item: OnboardItem,
    screenHeight: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = (screenHeight * 0.15).dp, end = 16.dp)
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = "${item.title} image",
            modifier = Modifier.width((screenHeight * 0.45).dp)
        )
        AppText(
            text = item.title,
            textStyle = h10SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp)
        )
        AppText(
            text = item.description,
            textStyle = bodyText14Regular,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
        )
    }
}