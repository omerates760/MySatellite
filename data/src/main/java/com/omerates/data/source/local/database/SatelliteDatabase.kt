package com.omerates.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omerates.data.source.local.dao.PositionDao
import com.omerates.data.source.local.dao.SatelliteDao
import com.omerates.data.source.local.dao.SatelliteDetailDao
import com.omerates.satelliteapp.core.data.model.entity.PositionEntity
import com.omerates.satelliteapp.core.data.model.entity.SatelliteDetailEntity
import com.omerates.satelliteapp.core.data.model.entity.SatelliteEntity

@Database(
    entities = [
        SatelliteEntity::class,
        SatelliteDetailEntity::class,
        PositionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SatelliteDatabase : RoomDatabase() {

    abstract fun SatelliteDao(): SatelliteDao
    abstract fun SatelliteDetailDao(): SatelliteDetailDao
    abstract fun PositionDao(): PositionDao

    companion object {
        @Volatile
        private var instance: SatelliteDatabase? = null
        fun getDatabase(context: Context): SatelliteDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, SatelliteDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

        private const val SOURCE_FILE_NAME = "satellites.json"
        private const val DB_NAME = "SatelliteDatabase"
    }

}