package com.fangga.savedresult.presentation.state

import com.fangga.core.data.model.result.ResultList

data class SavedResultState(
    val results: List<ResultList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
