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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    val maxImageHeight = (screenHeight * 0.65).dp

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(color = Color.White)
    ) {

        // Result image
        Box(modifier = Modifier.heightIn(max = maxImageHeight)) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.heightIn(max = maxImageHeight)
            ) {
                AppImage(
                    contentScale = ContentScale.FillWidth,
                    imageUrl = scanResult.image,
                    contentDescription = "Banana image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxImageHeight)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxImageHeight)
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
        }

        // Result data
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            Spacer(Modifier.height(24.dp))

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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

