package com.fangga.savedresult.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.core.utils.saveBitmapToFileAndGetUri
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
                    is Resource.Empty -> {
                        Log.d("SavedResultViewModel", "loadSavedResult: Empty")
                        updateUiState { copy(isLoading = false) }
                    }

                    is Resource.Error -> {
                        Log.d("SavedResultViewModel", "loadSavedResult: ${result.message}")
                        updateUiState {
                            copy(
                                isLoading = false,
                                errorMessage = result.message.toString(),
                            )
                        }
                    }

                    is Resource.Loading -> {
                        Log.d("SavedResultViewModel", "loadSavedResult: Loading")
                        updateUiState { copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        Log.d("SavedResultViewModel", "loadSavedResult: Success")
                        updateUiState { copy(isLoading = false, results = result.data!!) }
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

    private fun deleteResult(resultId: String) {
        viewModelScope.launch {
            localDataSource.deleteScanResultById(resultId).collectLatest { result ->
                when (result) {
                    is Resource.Empty -> updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = "Terjadi kesalahan",
                            showErrorToast = true
                        )
                    }

                    is Resource.Error -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                errorMessage = result.message.toString(),
                                showErrorToast = true
                            )
                        }
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        loadSavedResult()
                        updateUiState {
                            copy(
                                isLoading = false,
                                successMessage = "Berhasil menghapus riwayat scan",
                                showSuccessToast = true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetail(context: Context, item: ScanResultList) {
        val capturedImage = item.image
        val capturedImageFile = saveBitmapToFileAndGetUri(context, capturedImage)
        val encodedImageUri = Uri.encode(capturedImageFile)

        navigator.navigateTo("scan_result/false/${item.resultId}/${encodedImageUri}/${item.bananaType}/${item.ripenessType}") {
            launchSingleTop = true
            restoreState = true
        }
    }

    override suspend fun handleEvent(event: SavedResultEvent) {
        when (event) {
            SavedResultEvent.LoadSavedResult -> loadSavedResult()
            SavedResultEvent.OnBackClicked -> onBackClicked()
            is SavedResultEvent.OnSwipeToDelete -> deleteResult(event.resultId)
            is SavedResultEvent.NavigateToDetail -> navigateToDetail(event.context, event.item)
        }
    }
}