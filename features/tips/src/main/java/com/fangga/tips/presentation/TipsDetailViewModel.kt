package com.fangga.tips.presentation

import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.tips.data.Static
import com.fangga.tips.presentation.event.TipsDetailEvent
import com.fangga.tips.presentation.state.TipsDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TipsDetailViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<TipsDetailState, TipsDetailEvent>(TipsDetailState()) {

    override suspend fun handleEvent(event: TipsDetailEvent) {
        when (event) {
            is TipsDetailEvent.LoadTipsData -> updateUiState { copy(tipsData = Static.tipsDetail.find { it.tipsId == (event.tipsId) }) }
            TipsDetailEvent.OnBackClick -> navigator.goBack()
        }
    }
}