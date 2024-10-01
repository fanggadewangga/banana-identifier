package com.fangga.termscondition

import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.termscondition.event.TermsConditionEvent
import com.fangga.termscondition.state.TermsConditionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TermsConditionViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<TermsConditionState, TermsConditionEvent>(TermsConditionState()) {
    override suspend fun handleEvent(event: TermsConditionEvent) {
        when (event) {
            is TermsConditionEvent.OnBackClick -> navigator.goBack()
        }
    }
}