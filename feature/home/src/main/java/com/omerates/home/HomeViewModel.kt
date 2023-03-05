package com.omerates.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: State<HomeUiState> = _uiState

    fun onEvent(event: HomeEvent) = when (event) {
        is HomeEvent.QueryChangeEvent -> {
        }
        HomeEvent.ExecuteSearchEvent -> {
        }
    }
}