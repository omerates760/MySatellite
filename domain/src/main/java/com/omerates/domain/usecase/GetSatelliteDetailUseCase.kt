package com.omerates.domain.usecase

import com.omerates.common.utils.Resource
import com.omerates.domain.model.SatelliteDetailDomainItem
import com.omerates.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
) {

    operator fun invoke(
        id: Int
    ): Flow<Resource<SatelliteDetailDomainItem>> = satelliteRepository.getSatelliteDetail(id).map {
        Resource.Success(it)
    }.catch {
        Resource.Error(Exception(it.message ?: it.localizedMessage))
    }
}