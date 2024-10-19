package com.fangga.savedresult.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fangga.core.components.feature.ResultItem
import com.fangga.core.data.model.enums.ResultItemSwipeType
import com.fangga.core.data.model.result.ScanResultList

@Composable
fun SavedResultItems(
    items: List<ScanResultList>,
    topPadding: Dp,
    onItemClicked: (String) -> Unit,
    onSwipeToDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = topPadding + 32.dp)
    ) {
        items(items) { result ->
            ResultItem(
                data = result,
                swipeType = ResultItemSwipeType.DELETE,
                onItemClicked = { onItemClicked(result.resultId) },
                onActionClicked = { onSwipeToDelete(result.resultId) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}