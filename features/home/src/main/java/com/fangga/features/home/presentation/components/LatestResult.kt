package com.fangga.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.components.feature.ResultItem
import com.fangga.core.model.enums.ResultItemSwipeType
import com.fangga.core.model.result.ResultList

@Composable
fun LatestResult(
    data: ResultList,
    swipeType: ResultItemSwipeType,
    onItemClicked: (String) -> Unit,
    onActionClicked: (String) -> Unit,
    screenHeight: Int,
    screenWidth: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        AppText(text = "Lihat dan simpan riwayat scan terbaru Anda!", color = Color.White)
        ResultItem(
            data = data,
            swipeType = swipeType,
            onItemClicked = { onItemClicked(data.resultId) },
            onActionClicked = { onActionClicked(data.resultId) },
            modifier = Modifier.height((screenHeight * 0.125).dp)
        )
    }
}