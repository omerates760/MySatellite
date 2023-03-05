package com.omerates.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerates.common.utils.Resource
import com.omerates.domain.model.SatelliteDomainItem
import com.omerates.domain.usecase.SearchSatelliteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchSatelliteUseCase: SearchSatelliteUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: State<HomeUiState> = _uiState

    init {
        searchSatellite()
    }

    private fun searchSatellite(name: String = "") = viewModelScope.launch {
        searchSatelliteUseCase(name)
            .debounce(SEARCH_QUERY_LIMIT_DEBOUNCE)
            .onStart { emit(Resource.Loading) }
            .distinctUntilChanged()
            .collectLatest(::handleSearchSatelliteResponse)
    }

    private fun handleSearchSatelliteResponse(
        response: Resource<List<SatelliteDomainItem>>
    ) {
        when (response) {
            is Resource.Error -> _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = response.exception.message.orEmpty()
            )
            Resource.Loading -> _uiState.value = _uiState.value.copy(
                isLoading = true
            )
            is Resource.Success -> _uiState.value = _uiState.value.copy(
                isLoading = false,
                satellites = response.data.toSatelliteListUiModel()
            )
        }
    }

    fun onEvent(event: HomeEvent) = when (event) {
        is HomeEvent.QueryChangeEvent -> {
            _uiState.value = _uiState.value.copy(searchQuery = event.newQuery)
            searchSatellite(event.newQuery)
        }
        HomeEvent.ExecuteSearchEvent -> {
            searchSatellite(_uiState.value.searchQuery)
        }
    }

    companion object {
        private const val SEARCH_QUERY_LIMIT_DEBOUNCE = 600L
    }
}