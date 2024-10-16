package com.fangga.result.presentation.state

import com.fangga.core.data.model.result.ScanResult

data class ResultState(
    val isShowModal: Boolean = true,
    val isShowDeletionConfirmation: Boolean = false,
    val scanResult: ScanResult? = null,
)
