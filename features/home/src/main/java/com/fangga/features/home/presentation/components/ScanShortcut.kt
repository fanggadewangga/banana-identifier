package com.fangga.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppButton
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText12Medium
import com.fangga.core.resource.h8Medium
import com.fangga.core.resource.scanIcon
import com.fangga.core.resource.scanShortcut

@Composable
fun ScanShortcut(onClick: () -> Unit, modifier: Modifier = Modifier) {

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {
            AppImage(
                imageUrl = scanShortcut,
                contentDescription = "Scan shortcut image",
                modifier = Modifier.weight(0.6f)
            )
            Spacer(modifier = Modifier.width(18.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier.weight(1f)
            ) {
                AppText(
                    text = "Mau Tahu Pisang Kamu Sudah Matang atau Belum?",
                    textStyle = bodyText12Medium
                )
                AppButton(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AppImage(
                            imageUrl = scanIcon,
                            contentDescription = "Scan Icon",
                            modifier = Modifier.size(20.dp)
                        )
                        AppText(
                            text = "Ayo scan sekarang!",
                            textStyle = h8Medium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}