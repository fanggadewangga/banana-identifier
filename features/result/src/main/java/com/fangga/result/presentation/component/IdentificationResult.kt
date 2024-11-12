package com.fangga.result.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.resource.bananaTypeIndicator
import com.fangga.core.resource.h11SemiBold
import com.fangga.core.resource.ripenessTypeIndicator

@Composable
fun IdentificationResult(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    isNewResult: Boolean,
    scanResult: ScanResult,
    onRepeatScan: () -> Unit,
    onSaveResult: () -> Unit,
    onShowDeletionConfirmation: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            AppText(
                text = "Hasil Identifikasi",
                textStyle = h11SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            IdentificationIndicator(
                indicator = "Jenis Pisang",
                value = scanResult.bananaType,
                icon = bananaTypeIndicator,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            IdentificationIndicator(
                indicator = "Tipe & Tingkat Kematangan",
                value = scanResult.ripenessType,
                icon = ripenessTypeIndicator,
                modifier = Modifier.fillMaxWidth()
            )
        }
        IdentificationActionButtons(
            isNewResult = isNewResult,
            screenWidth = screenWidth,
            onRepeatScan = { onRepeatScan() },
            onSaveResult = { onSaveResult() },
            onDeleteSavedResult = { onShowDeletionConfirmation() },
            modifier = Modifier.fillMaxWidth()
        )
    }
}