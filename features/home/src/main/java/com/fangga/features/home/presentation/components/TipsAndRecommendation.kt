package com.fangga.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.h11SemiBold
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

@Composable
fun TipsAndRecommendation(
    tipsData: List<ListTipsAndRecommendation>,
    onTipsItemClicked: (String) -> Unit,
    screenWidth: Int,
    screenHeight: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        AppText(text = "Tips & Rekomendasi", textStyle = h11SemiBold)
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                count = tipsData.size,
                key = { index -> tipsData[index].id },
                itemContent = { index ->
                    val tips = tipsData[index]
                    TipsAndRecommendationItem(
                        item = tips,
                        onClick = { onTipsItemClicked(tips.id) },
                        modifier = Modifier
                            .width(( screenWidth * 0.7 ).dp)
                            .height(( screenHeight * 0.2 ).dp)
                            .padding(end = 16.dp)
                    )
                }
            )
        }
    }
}