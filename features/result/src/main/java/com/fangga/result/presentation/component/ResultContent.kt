package com.fangga.result.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.snap
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.model.result.Result
import com.fangga.core.resource.backIcon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    result: Result,
    isNewResult: Boolean,
    isShowBottomSheet: Boolean,
    isShowDeletionConfirmation: Boolean,
    onRepeatScan: () -> Unit,
    onSaveResult: () -> Unit,
    onDeleteSavedResult: () -> Unit,
    onBackClick: () -> Unit,
    onDismiss: () -> Unit,
    onCancelDelete: () -> Unit,
    onShowDeletionConfirmation: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        AppImage(
            contentScale = ContentScale.FillHeight,
            imageUrl = result.image,
            contentDescription = "Banana image",
            modifier = Modifier.fillMaxHeight()
        )
        AppImage(
            imageUrl = backIcon,
            contentDescription = "Back Icon",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .padding(start = 24.dp, top = 48.dp)
                .size(24.dp)
                .clickable { onBackClick() }
        )

        if (isShowBottomSheet) {
            ModalBottomSheet(
                containerColor = Color.White,
                sheetState = sheetState,
                onDismissRequest = {
                    scope.launch { sheetState.show() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                },
                modifier = Modifier.wrapContentSize()
            ) {
                AnimatedVisibility(
                    visible = isShowDeletionConfirmation,
                    enter = fadeIn(animationSpec = snap()) + slideInVertically(animationSpec = snap()),
                ) {
                    DeletionConfirmation(
                        screenWidth = screenWidth,
                        bananaType = result.bananaType,
                        onCancelClicked = { onCancelDelete() },
                        onDeleteClicked = { onDeleteSavedResult() }
                    )
                }
                AnimatedVisibility(
                    visible = !isShowDeletionConfirmation,
                    enter = fadeIn(animationSpec = snap()) + slideInVertically(animationSpec = snap()),
                ) {
                    IdentificationResult(
                        isNewResult = isNewResult,
                        screenWidth = screenWidth,
                        result = result,
                        onRepeatScan = { onRepeatScan() },
                        onSaveResult = { onSaveResult() },
                        onShowDeletionConfirmation = { onShowDeletionConfirmation() },
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }
    }
}