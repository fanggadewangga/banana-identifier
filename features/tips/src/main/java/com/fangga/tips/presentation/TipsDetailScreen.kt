package com.fangga.tips.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fangga.core.components.common.AppAnnotatedText
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText12Regular
import com.fangga.core.resource.tosca100
import com.fangga.tips.data.Static
import com.fangga.tips.presentation.component.TipsDetailContent
import com.fangga.tips.presentation.event.TipsDetailEvent

@Composable
fun TipsDetailScreen(
    tipsId: String,
    screenWidth: Int,
    screenHeight: Int,
) {

    val viewModel = hiltViewModel<TipsDetailViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val tipsData = state.value.tipsData

    LaunchedEffect(true) {
        viewModel.onEvent(TipsDetailEvent.LoadTipsData(tipsId))
    }

    if (tipsData != null)
        TipsDetailContent(
            tipsId = tipsId,
            imageSource = tipsData.imageSource,
            title = tipsData.title,
            description = {
                if (tipsData.tipsId == Static.tipsDetail.first().tipsId) {
                    val annotatedDescription = buildAnnotatedString {
                        append("Mendapatkan hasil scan yang akurat adalah kunci untuk mengetahui jenis dan tingkat kematangan pisang dengan tepat. Aplikasi kami dapat mengenali berbagai jenis pisang seperti ")
                        withStyle(style = SpanStyle(color = tosca100)) {
                            append("Ambon, Lumut, Morosebo, Kepok, Hijau, Susu,")
                        }
                        append(" dan ")
                        withStyle(style = SpanStyle(color = tosca100)) {
                            append("Raja")
                        }
                        append(". Berikut adalah beberapa tips mudah untuk memastikan pemindaian Anda optimal:")
                    }
                    AppAnnotatedText(
                        text = annotatedDescription,
                        textStyle = bodyText12Regular,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                } else {
                    AppText(
                        text = tipsData.description,
                        textStyle = bodyText12Regular,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            },
            steps = tipsData.steps,
            onBackClick = {
                viewModel.onEvent(TipsDetailEvent.OnBackClick)
            },
            modifier = Modifier.fillMaxWidth()
        )
}