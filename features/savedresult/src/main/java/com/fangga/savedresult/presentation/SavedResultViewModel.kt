package com.fangga.savedresult.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.savedresult.presentation.event.SavedResultEvent
import com.fangga.savedresult.presentation.state.SavedResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedResultViewModel @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val navigator: NavigationService
) : BaseViewModel<SavedResultState, SavedResultEvent>(SavedResultState()) {

    private fun loadSavedResult() {
        viewModelScope.launch {
            localDataSource.getAllScanResults().collectLatest { result ->
                when (result) {
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> updateUiState {
                        copy(
                            isLoading = false,
                            results = result.data!!
                        )
                    }
                }
            }
        }
    }

    private fun onBackClicked() {
        navigator.navigateTo("home") {
            popUpTo("home") {
                inclusive = true
            }
        }
    }

    private fun deleteResult(context: Context, resultId: String) {
        viewModelScope.launch {
            localDataSource.deleteScanResultById(resultId).collectLatest { result ->
                when (result) {
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> {
                        updateUiState { copy(isLoading = false, error = result.message.toString()) }
                        Log.d("SavedResultViewModel", "deleteResult: Error ${result.message}")
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        loadSavedResult()
                        Toast.makeText(context, "Berhasil menghapus data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun navigateToDetail(resultId: String) {

    }

    override suspend fun handleEvent(event: SavedResultEvent) {
        when (event) {
            SavedResultEvent.LoadSavedResult -> loadSavedResult()
            SavedResultEvent.OnBackClicked -> onBackClicked()
            is SavedResultEvent.OnSwipeToDelete -> deleteResult(event.context, event.resultId)
            is SavedResultEvent.NavigateToDetail -> navigateToDetail(event.resultId)
        }
    }
}