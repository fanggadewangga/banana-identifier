package com.fangga.result.presentation.state

import com.fangga.core.data.model.result.ScanResult

data class ResultState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String? = null,
    val isShowModal: Boolean = true,
    val isShowDeletionConfirmation: Boolean = false,
    val scanResult: ScanResult? = null,
)
