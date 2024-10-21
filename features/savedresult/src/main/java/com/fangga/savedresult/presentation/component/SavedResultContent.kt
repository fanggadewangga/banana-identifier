package com.fangga.savedresult.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.resource.emptyResultImage

@Composable
fun SavedResultContent(
    results: List<ScanResultList>,
    topPadding: Dp,
    onSwipeToDelete: (String) -> Unit,
    onNavigateToDetail: (ScanResultList) -> Unit,
    modifier: Modifier = Modifier
) {
    if (results.isEmpty())
        EmptyResult(
            imageSource = emptyResultImage,
            message = "Oops, belum ada data identifikasi pisang yang tersimpan",
            modifier = modifier
        )
    else
        SavedResultItems(
            items = results,
            topPadding = topPadding,
            onItemClicked = { onNavigateToDetail(it) },
            onSwipeToDelete = { onSwipeToDelete(it) },
            modifier = modifier
        )
}