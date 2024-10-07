package com.fangga.result.presentation.state

import com.fangga.core.model.result.Result

data class ResultState(
    val isShowModal: Boolean = true,
    val isShowDeletionConfirmation: Boolean = false,
    val result: Result? = null,
)
