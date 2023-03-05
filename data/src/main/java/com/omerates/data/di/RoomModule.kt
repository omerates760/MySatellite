package com.omerates.data.di

import android.content.Context
import com.omerates.data.source.local.database.SatelliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        SatelliteDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun providesSatelliteDao(database: SatelliteDatabase) = database.SatelliteDao()

    @Singleton
    @Provides
    fun providesSatelliteDetailDao(database: SatelliteDatabase) = database.SatelliteDetailDao()

    @Singleton
    @Provides
    fun providesPositionsDao(database: SatelliteDatabase) = database.PositionDao()
}