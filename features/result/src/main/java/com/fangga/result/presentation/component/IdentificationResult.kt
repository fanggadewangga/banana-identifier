package com.fangga.result.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.model.result.Result
import com.fangga.core.resource.bananaTypeIndicator
import com.fangga.core.resource.h11SemiBold
import com.fangga.core.resource.ripenessTypeIndicator
import com.fangga.core.utils.toDescription

@Composable
fun IdentificationResult(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    isNewResult: Boolean,
    result: Result,
    onRepeatScan: () -> Unit,
    onSaveResult: () -> Unit,
    onDeleteSavedResult: () -> Unit,
) {
    Column(modifier = modifier) {
        AppText(
            text = "Hasil Identifikasi",
            textStyle = h11SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        IdentificationIndicator(
            indicator = "Jenis Pisang",
            value = result.bananaType.toDescription(),
            icon = bananaTypeIndicator,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        IdentificationIndicator(
            indicator = "Tipe & Tingkat Kematangan",
            value = result.ripenessType.toDescription(),
            icon = ripenessTypeIndicator,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        IdentificationActionButtons(
            isNewResult = isNewResult,
            screenWidth = screenWidth,
            onRepeatScan = { onRepeatScan() },
            onSaveResult = { onSaveResult() },
            onDeleteSavedResult = { onDeleteSavedResult() },
            modifier = Modifier.fillMaxWidth()
        )
    }
}