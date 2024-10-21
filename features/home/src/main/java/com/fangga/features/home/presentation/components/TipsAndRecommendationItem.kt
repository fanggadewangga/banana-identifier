package com.fangga.features.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText14Medium
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

@Composable
fun TipsAndRecommendationItem(
    modifier: Modifier = Modifier,
    item: ListTipsAndRecommendation,
    onClick: (String) -> Unit
) {
    val gradientHeight = 260.dp
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = 0f,
        endY = with(LocalDensity.current) { gradientHeight.toPx() }
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick(item.id) },
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            AppImage(
                imageUrl = item.image,
                contentDescription = "${item.title} Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithContent {
                        drawContent()
                        drawRect(brush = gradientBrush)
                    }
            )
            AppText(
                text = item.title,
                textStyle = bodyText14Medium,
                color = Color.White,
                maxLine = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
            )
        }
    }
}