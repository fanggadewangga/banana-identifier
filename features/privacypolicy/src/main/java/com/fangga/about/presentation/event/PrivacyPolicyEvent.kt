package com.fangga.about.presentation.event

sealed class PrivacyPolicyEvent {
    data object OnBackClick: PrivacyPolicyEvent()
}