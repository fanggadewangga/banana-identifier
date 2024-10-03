package com.fangga.savedresult.presentation.state

import com.fangga.core.model.result.ResultList

data class SavedResultState(
    val results: List<ResultList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
