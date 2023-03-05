package com.omerates.data.di

import com.omerates.data.repository.SatelliteRepositoryImpl
import com.omerates.domain.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SatelliteRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(satelliteRepositoryImpl: SatelliteRepositoryImpl): SatelliteRepository
}