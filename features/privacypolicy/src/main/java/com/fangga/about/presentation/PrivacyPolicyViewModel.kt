package com.fangga.about.presentation

import com.fangga.about.presentation.event.PrivacyPolicyEvent
import com.fangga.about.presentation.state.PrivacyPolicyState
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivacyPolicyViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<PrivacyPolicyState, PrivacyPolicyEvent>(PrivacyPolicyState()) {

    override suspend fun handleEvent(event: PrivacyPolicyEvent) {
        when (event) {
            is PrivacyPolicyEvent.OnBackClick -> navigator.goBack()
        }
    }
}