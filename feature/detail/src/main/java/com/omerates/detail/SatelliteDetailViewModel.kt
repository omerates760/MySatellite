package com.omerates.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = mutableStateOf(SatelliteDetailUiState())
    val uiState: State<SatelliteDetailUiState> = _uiState
}