package com.fangga.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.h11SemiBold
import com.fangga.features.home.data.static.Static

@Composable
fun AboutApp(
    onClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        AppText(text = "Tentang Aplikasi", textStyle = h11SemiBold)
        Spacer(modifier = Modifier.height(16.dp))
        Static.aboutApp.forEach { item ->
            AboutAppItem(
                item = item,
                onClick = { onClicked(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
    }
}