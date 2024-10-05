package com.fangga.result.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppButton
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.redMainDanger

@Composable
fun IdentificationActionButtons(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    isNewResult: Boolean,
    onRepeatScan: () -> Unit,
    onSaveResult: () -> Unit,
    onDeleteSavedResult: () -> Unit,
) {
    if (isNewResult)
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            AppButton(
                backgroundColor = Color.White,
                borderColor = greenPrimary,
                onClick = { onRepeatScan() },
                borderWidth = 1.3.dp,
                modifier = Modifier.width(( screenWidth * 0.4 ).dp)
            ) {
                AppText(text = "Scan Ulang", textStyle = bodyText14Regular, color = greenPrimary)
            }
            AppButton(
                backgroundColor = Color.White,
                borderColor = greenPrimary,
                onClick = { onSaveResult() },
                borderWidth = 1.3.dp,
                modifier = Modifier.width(( screenWidth * 0.4 ).dp)
            ) {
                AppText(text = "Simpan", textStyle = bodyText14Regular, color = greenPrimary)
            }
        }
    else
        AppButton(
            backgroundColor = Color.White,
            borderColor = redMainDanger,
            onClick = { onDeleteSavedResult() },
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(text = "Hapus", textStyle = bodyText14Regular, color = redMainDanger)
        }
}