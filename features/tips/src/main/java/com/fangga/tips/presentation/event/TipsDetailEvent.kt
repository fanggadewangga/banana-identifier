package com.fangga.tips.presentation.event

sealed class TipsDetailEvent {
    data class LoadTipsData(val tipsId: String): TipsDetailEvent()
    data object OnBackClick: TipsDetailEvent()
}