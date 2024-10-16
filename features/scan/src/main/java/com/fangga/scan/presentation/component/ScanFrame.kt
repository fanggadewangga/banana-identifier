package com.fangga.scan.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.scanFrame

@Composable
fun ScanFrame(
    modifier: Modifier = Modifier,
    isSheetOpen: Boolean,
    screenHeight: Int,
    screenWidth: Int,
) {
    val frameHeight = screenHeight * 0.7f
    val infiniteTransition = rememberInfiniteTransition(label = "scan frame animation")
    val animationDuration = 7000
    val heightAnimation by infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = frameHeight.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDuration
                20.dp at 0 using FastOutSlowInEasing
                frameHeight.dp - 27.dp at animationDuration / 2 using FastOutSlowInEasing
                20.dp at animationDuration using FastOutSlowInEasing
            }
        ),
        label = "scan frame animation"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {

            // Scan frame image
            AppImage(
                imageUrl = scanFrame,
                contentDescription = "Scan frame",
                modifier = Modifier
                    .height(frameHeight.dp)
                    .align(Alignment.Center)
            )

            // Animated scan line and expanding background

            this@Column.AnimatedVisibility(visible = !isSheetOpen) {
                Column(
                    modifier = Modifier
                        .width((screenWidth * 0.79.dp))
                        .height(frameHeight.dp - 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Spacer to push the Divider upwards
                    Spacer(modifier = Modifier.weight(1f))

                    // Divider (Scan Line)
                    HorizontalDivider(
                        thickness = 4.dp,
                        color = greenPrimary,
                        modifier = Modifier.width((screenWidth * 0.73.dp))
                    )

                    // Expanding background starting from the bottom
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightAnimation + 10.dp)
                            .background(
                                color = Color.LightGray.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(24.dp)
                            )
                    )
                }
            }
        }
        AppText(
            text = "Tempatkan gambar dalam frame",
            textStyle = bodyText14Regular,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}