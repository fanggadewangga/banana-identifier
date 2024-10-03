package com.fangga.savedresult.presentation

import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.savedresult.presentation.event.SavedResultEvent
import com.fangga.savedresult.presentation.state.SavedResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedResultViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<SavedResultState, SavedResultEvent>(SavedResultState()) {

    private fun loadSavedResult() {

    }

    private fun onBackClicked() {
        navigator.navigateTo("home") {
            popUpTo("home") {
                inclusive = true
            }
        }
    }

    private fun deleteResult(resultId: String) {

    }

    private fun navigateToDetail(resultId: String) {

    }

    override suspend fun handleEvent(event: SavedResultEvent) {
        when (event) {
            SavedResultEvent.LoadSavedResult -> loadSavedResult()
            SavedResultEvent.OnBackClicked -> onBackClicked()
            is SavedResultEvent.OnSwipeToDelete -> deleteResult(event.resultId)
            is SavedResultEvent.NavigateToDetail -> navigateToDetail(event.resultId)
        }
    }
}