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
import com.fangga.core.resource.redMainDanger

@Composable
fun DeletionActionButtons(
    modifier: Modifier = Modifier,
    screenWidth: Int,
    onCancelClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        AppButton(
            backgroundColor = Color.White,
            borderColor = redMainDanger,
            onClick = { onCancelClicked() },
            borderWidth = 1.3.dp,
            modifier = Modifier.width((screenWidth * 0.4).dp)
        ) {
            AppText(text = "Batal", textStyle = bodyText14Regular, color = redMainDanger)
        }
        AppButton(
            backgroundColor = redMainDanger,
            borderColor = redMainDanger,
            onClick = { onDeleteClicked() },
            borderWidth = 1.3.dp,
            modifier = Modifier.width((screenWidth * 0.4).dp)
        ) {
            AppText(text = "Hapus", textStyle = bodyText14Regular, color = Color.White)
        }
    }
}