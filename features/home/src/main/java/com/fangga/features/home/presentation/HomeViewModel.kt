package com.fangga.features.home.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.core.utils.Constants.LATEST_RESULT_ID
import com.fangga.core.utils.toScanResultEntity
import com.fangga.features.home.presentation.event.HomeEvent
import com.fangga.features.home.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val navigationService: NavigationService
) : BaseViewModel<HomeState, HomeEvent>(HomeState()) {

    private fun loadLatestResult() {
        viewModelScope.launch {
            localDataSource.getLatestScanResult().collect { result ->
                when (result) {
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> updateUiState {
                        copy(
                            isLoading = false,
                            latestResult = result.data
                        )
                    }
                }
            }
        }
    }

    private fun deleteLatestResult() {
        viewModelScope.launch {
            localDataSource.deleteScanResultById(LATEST_RESULT_ID).collect { result ->
                when (result) {
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        loadLatestResult()
                    }
                }
            }
        }
    }

    private fun handleScanShortcutClick() {
        navigationService.navigateTo("scan_camera")
    }

    private fun handleLatestResultClick(resultId: String) {
        navigationService.navigateTo("saved_result")
        Log.d("HomeViewModel", "handleLatestResultClick: $resultId")
    }

    private fun handleLatestResultSwiped(context: Context, latestResult: ScanResultList) {
        val entity = latestResult.toScanResultEntity()
        entity.resultId = UUID.randomUUID().toString()

        viewModelScope.launch {
            localDataSource.insertNewScanResult(entity).collectLatest { result ->
                when (result) {
                    is Resource.Empty -> {
                        updateUiState { copy(isLoading = false) }
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Error -> {
                        updateUiState { copy(isLoading = false, error = result.message) }
                        Toast.makeText(context, "Gagal menyimpan hasil", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        deleteLatestResult()
                        Toast.makeText(context, "Berhasil menyimpan hasil", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun handleTipsItemClicked(tipsId: String) {
        navigationService.navigateTo("tips/${tipsId}") {
            launchSingleTop = true
            restoreState = true
        }
    }

    private fun handleAboutAppClicked(aboutId: String) {
        when (aboutId) {
            // TODO : Handle Rating Navigation
            "privacy_policy" -> navigationService.navigateTo("privacy_and_policy")
            "terms_and_conditions" -> navigationService.navigateTo("terms_and_condition")
            "rating" -> {}
        }
    }

    override suspend fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadLatestResult -> loadLatestResult()
            HomeEvent.OnScanShortcutClicked -> handleScanShortcutClick()
            is HomeEvent.OnLatestResultClicked -> handleLatestResultClick(event.resultId)
            is HomeEvent.OnLatestResultSwiped -> handleLatestResultSwiped(
                event.context,
                event.latestResult
            )

            is HomeEvent.OnTipsItemClicked -> handleTipsItemClicked(event.tipsId)
            is HomeEvent.OnAboutAppClicked -> handleAboutAppClicked(event.aboutId)
        }
    }
}