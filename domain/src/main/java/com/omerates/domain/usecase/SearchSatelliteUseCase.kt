package com.omerates.domain.usecase

import com.omerates.common.utils.Resource
import com.omerates.domain.model.SatelliteDomainItem
import com.omerates.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchSatelliteUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
) {

    operator fun invoke(
        name: String
    ): Flow<Resource<List<SatelliteDomainItem>>> = satelliteRepository.searchSatellite(name).map {
        Resource.Success(it)
    }.catch {
        Resource.Error(Exception(it.message ?: it.localizedMessage))
    }
}