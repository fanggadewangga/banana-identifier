package com.fangga.features.home.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fangga.core.components.common.AppImage
import com.fangga.core.resource.homeBackground

@Composable
fun HomeBackground(modifier: Modifier = Modifier) {
    AppImage(imageUrl = homeBackground, contentDescription = "Home background", modifier = modifier)
}