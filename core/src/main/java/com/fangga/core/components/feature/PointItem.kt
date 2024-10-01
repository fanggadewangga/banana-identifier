package com.fangga.core.components.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText12SemiBold
import com.fangga.core.resource.greenPrimary

@Composable
fun PointItem(
    modifier: Modifier = Modifier,
    number: String,
    numberShape: Shape = CircleShape,
    numberBackgroundColor: Color = greenPrimary,
    numberTextColor: Color = Color.White,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
    ) {
        NumberingItem(
            number = number,
            shape = numberShape,
            backgroundColor = numberBackgroundColor,
            textColor = numberTextColor,
            modifier = Modifier.size(32.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            AppText(text = title, textStyle = bodyText12SemiBold, color = Color.Black)
            content()
        }
    }
}