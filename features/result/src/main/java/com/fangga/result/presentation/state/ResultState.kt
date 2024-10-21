package com.fangga.result.presentation.state

import com.fangga.core.data.model.result.ScanResult

data class ResultState(
    val isLoading: Boolean = false,
    val isShowModal: Boolean = true,
    val isShowDeletionConfirmation: Boolean = false,
    val scanResult: ScanResult? = null,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val showSuccessToast: Boolean = false,
    val showErrorToast: Boolean = false,
)
