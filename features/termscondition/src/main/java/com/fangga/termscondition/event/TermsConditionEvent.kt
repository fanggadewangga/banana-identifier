package com.fangga.termscondition.event

sealed class TermsConditionEvent {
    data object OnBackClick : TermsConditionEvent()
}