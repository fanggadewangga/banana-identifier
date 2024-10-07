package com.fangga.result.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.model.enums.BananaType
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.deleteConfirmationImage
import com.fangga.core.resource.h11SemiBold
import com.fangga.core.utils.toDescription

@Composable
fun DeletionConfirmation(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    bananaType: BananaType,
    onCancelClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        modifier = modifier.fillMaxWidth()
    ) {
        AppText(
            text = bananaType.toDescription(),
            textStyle = h11SemiBold,
            textAlign = TextAlign.Center
        )
        AppImage(
            imageUrl = deleteConfirmationImage,
            contentDescription = "Delete confirmation image",
            modifier = Modifier.size(160.dp)
        )
        AppText(
            text = "Apakah Anda yakin ingin menghapus hasil data identifikasi ini?",
            textStyle = bodyText14Regular,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        DeletionActionButtons(
            screenWidth = screenWidth,
            onCancelClicked = { onCancelClicked() },
            onDeleteClicked = { onDeleteClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
    }
}