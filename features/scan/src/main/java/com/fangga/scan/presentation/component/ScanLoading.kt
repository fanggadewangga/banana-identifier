package com.fangga.scan.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppLinearProgressIndicator
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.h11SemiBold
import com.fangga.core.resource.h12Bold
import com.fangga.core.resource.scanLoading

@Composable
fun ScanLoading(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {

    /*
    * Animate progress from 0 to 0.8 (80%) when isLoading = true  and from 0.8 to 1 (100%) when isLoading = false
    * */
    val progress = remember { Animatable(0f) }
    LaunchedEffect(key1 = true, key2 = isLoading) {
        if (isLoading) {
            progress.animateTo(
                targetValue = 0.8f,
                animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
            )
        } else {
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
        }
    }
    val progressPercentage = (progress.value * 100).toInt()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        modifier = modifier.padding(16.dp)
    ) {
        AppText(text = "Mengidentifikasi", textStyle = h11SemiBold)
        AppImage(
            imageUrl = scanLoading,
            contentDescription = "Loading",
            modifier = Modifier.width(165.dp)
        )
        AppLinearProgressIndicator(
            progress = progress.value,
            modifier = Modifier.fillMaxWidth()
        )
        AppText(
            text = "$progressPercentage%",
            textStyle = h12Bold,
            color = greenPrimary
        )
    }
}