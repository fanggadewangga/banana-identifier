package com.fangga.core.components.common.apptoast

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText

object AppToastUtil {

    @Composable
    fun ShowAppToast(
        message: String,
        type: AppToastProperty,
        duration: Int = Toast.LENGTH_SHORT,
        padding: PaddingValues = PaddingValues(12.dp),
        contentAlignment: Alignment = Alignment.BottomCenter,
    ) {
        val toast = AppToast(LocalContext.current)
        toast.MakeToast(
            message = message,
            duration = duration,
            type = type,
            paddingValues = padding,
            contentAlignment = contentAlignment
        )
        toast.show()
    }

    @Composable
    fun SetView(
        message: String,
        backgroundColor: Color,
        textColor: Color,
        padding: PaddingValues,
        contentAlignment: Alignment
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = contentAlignment
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentSize(),
                color = Color.Transparent
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .defaultMinSize(minHeight = 44.dp)
                        .fillMaxWidth()
                        .background(color = backgroundColor)
                        .padding(12.dp),
                ) {
                    AppText(
                        text = message,
                        textAlign = TextAlign.Center,
                        color = textColor,
                    )
                }
            }
        }
    }
}