package com.omerates.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerates.common.launchPeriodicAsync
import com.omerates.common.utils.Resource
import com.omerates.domain.model.PositionDomainItem
import com.omerates.domain.model.SatelliteDetailDomainItem
import com.omerates.domain.usecase.GetPositionsByIdUseCase
import com.omerates.domain.usecase.GetSatelliteDetailUseCase
import com.omerates.domain.usecase.GetSatelliteNameByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatelliteNameByIdUseCase: GetSatelliteNameByIdUseCase,
    private val getPositionsByIdUseCase: GetPositionsByIdUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf(SatelliteDetailUiState())
    val uiState: State<SatelliteDetailUiState> = _uiState

    private var positionJob: Job? = null

    init {
        getSatelliteDetail()
        getSatellitePositions()
    }

    private fun getSatelliteDetail() = viewModelScope.launch {
        stateHandle.get<Int>("satelliteId")?.let { satelliteId ->
            getSatelliteDetailUseCase(satelliteId).zip(
                getSatelliteNameByIdUseCase(satelliteId)
            ) { satelliteDetail, satelliteName ->
                updateSatelliteUiModel(satelliteDetail, satelliteName)
            }.onStart {
                _uiState.value = _uiState.value.copy(
                    isLoading = true
                )
            }.catch {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = it.message.orEmpty()
                )
            }.collect()
        }
    }

    private fun updateSatelliteUiModel(
        satelliteDetail: Resource<SatelliteDetailDomainItem>,
        satelliteName: Resource<String>
    ) {
        val satellite = satelliteDetail.toData()?.toSatelliteUiModel()?.copy(
            name = satelliteName.toData().orEmpty()
        )
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            satelliteUiModel = satellite,
        )
    }

    private fun getSatellitePositions() = viewModelScope.launch {
        stateHandle.get<Int>("satelliteId")?.let { satelliteId ->
            getPositionsByIdUseCase(satelliteId)
                .onStart { emit(Resource.Loading) }
                .collectLatest(::handlePositionsResponse)
        }
    }

    private fun handlePositionsResponse(
        response: Resource<List<PositionDomainItem>>
    ) = when (response) {
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success -> {
            handlePositions(response.data)
        }
    }

    private fun handlePositions(positions: List<PositionDomainItem>) {
        positionJob = viewModelScope.launchPeriodicAsync(POSITION_UPDATE_PERIODIC_TIME) {
            val updatedPosition = positions.shuffled().first()
            updatePositionUiModel(updatedPosition)
        }
    }

    private fun updatePositionUiModel(
        updatedPosition: PositionDomainItem
    ) {
        _uiState.value = _uiState.value.copy(
            positions = PositionUiModel(updatedPosition.posX, updatedPosition.posY)
        )
    }

    override fun onCleared() {
        super.onCleared()
        positionJob?.cancel()
    }

    companion object {
        private const val POSITION_UPDATE_PERIODIC_TIME = 3000L
    }
}