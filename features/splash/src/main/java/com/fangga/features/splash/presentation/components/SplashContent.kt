package com.fangga.features.splash.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fangga.core.resource.SplashBackground
import com.fangga.core.resource.SplashLogo

@Composable
fun SplashContent(modifier: Modifier = Modifier, screenWidth: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = SplashBackground,
            contentScale = ContentScale.Crop,
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(56.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = SplashLogo,
                contentDescription = "App Logo",
                modifier = Modifier.width((screenWidth * 0.46).dp)
            )
        }
    }
}