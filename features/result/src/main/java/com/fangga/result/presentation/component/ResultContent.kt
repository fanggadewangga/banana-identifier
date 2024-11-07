package com.fangga.result.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.snap
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.resource.backIcon
import com.fangga.core.utils.noRippleClickable

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    scanResult: ScanResult,
    isNewResult: Boolean,
    isShowDeletionConfirmation: Boolean,
    onRepeatScan: () -> Unit,
    onSaveResult: () -> Unit,
    onDeleteSavedResult: () -> Unit,
    onBackClick: () -> Unit,
    onCancelDelete: () -> Unit,
    onShowDeletionConfirmation: () -> Unit,
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box(modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.74).dp)
        ) {
            AppImage(
                contentScale = ContentScale.FillBounds,
                imageUrl = scanResult.image,
                contentDescription = "Banana image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.74).dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.74).dp)
                    .background(Color.Black.copy(alpha = 0.5f))
            )
        }
        AppImage(
            imageUrl = backIcon,
            contentDescription = "Back Icon",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .padding(start = 24.dp, top = 48.dp)
                .size(24.dp)
                .noRippleClickable { onBackClick() }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp)
                    .navigationBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .width(64.dp)
                        .height(4.dp)
                        .background(
                            color = Color.LightGray, shape = RoundedCornerShape(100.dp)
                        )
                )
                Spacer(Modifier.height(12.dp))
                AnimatedVisibility(
                    visible = isShowDeletionConfirmation,
                    enter = fadeIn(animationSpec = snap()) + slideInVertically(animationSpec = snap()),
                ) {
                    DeletionConfirmation(
                        screenWidth = screenWidth,
                        bananaType = scanResult.bananaType,
                        onCancelClicked = { onCancelDelete() },
                        onDeleteClicked = { onDeleteSavedResult() },
                    )
                }
                AnimatedVisibility(
                    visible = !isShowDeletionConfirmation,
                    enter = fadeIn(animationSpec = snap()) + slideInVertically(animationSpec = snap()),
                ) {
                    IdentificationResult(
                        isNewResult = isNewResult,
                        screenWidth = screenWidth,
                        scanResult = scanResult,
                        onRepeatScan = { onRepeatScan() },
                        onSaveResult = { onSaveResult() },
                        onShowDeletionConfirmation = { onShowDeletionConfirmation() },
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
                    )
                }
            }
        }
    }
}