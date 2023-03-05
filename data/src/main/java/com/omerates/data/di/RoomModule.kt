package com.omerates.data.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omerates.common.extensions.readFile
import com.omerates.data.model.dto.SatelliteItem
import com.omerates.data.model.mapper.toSatelliteListEntity
import com.omerates.data.source.local.dao.PositionDao
import com.omerates.data.source.local.dao.SatelliteDao
import com.omerates.data.source.local.dao.SatelliteDetailDao
import com.omerates.satelliteapp.core.data.model.entity.PositionEntity
import com.omerates.satelliteapp.core.data.model.entity.SatelliteDetailEntity
import com.omerates.satelliteapp.core.data.model.entity.SatelliteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            val satellites = getSatelliteEntity(appContext)
                            getDatabase(appContext).SatelliteDao().insertAll(satellites)
                        }
                    }
                }).build()

        private fun getSatelliteEntity(appContext: Context): List<SatelliteEntity> {
            val jsonString = appContext.assets.readFile(SOURCE_FILE_NAME)
            val listType = object : TypeToken<ArrayList<SatelliteItem?>?>() {}.type
            val satellites: List<SatelliteItem> = Gson().fromJson(jsonString, listType)
            return satellites.toSatelliteListEntity()
        }

        private const val SOURCE_FILE_NAME = "satellites.json"
        private const val DB_NAME = "SatelliteDatabase"
    }

}