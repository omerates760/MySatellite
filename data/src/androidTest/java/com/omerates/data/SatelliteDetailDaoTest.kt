package com.omerates.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.omerates.data.model.entity.SatelliteDetailEntity
import com.omerates.data.source.local.dao.SatelliteDetailDao
import com.omerates.data.source.local.database.SatelliteDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SatelliteDetailDaoTest {
    private lateinit var database: SatelliteDatabase
    private lateinit var satelliteDetailDao: SatelliteDetailDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SatelliteDatabase::class.java
        ).allowMainThreadQueries().build()

        satelliteDetailDao = database.SatelliteDetailDao()
    }

    @Test
    fun getSatelliteDetail_whenNotFoundSatelliteDetailByIdReturnTrue() {
        val satelliteDetailList = listOf(
            SatelliteDetailEntity(
                id = 1,
                costPerLaunch = 1,
                firstFlight = "22-01-1998",
                height = 100,
                mass = 10000
            ),
            SatelliteDetailEntity(
                id = 3,
                costPerLaunch = 2,
                firstFlight = "22-01-1998",
                height = 100,
                mass = 10000
            ),
            SatelliteDetailEntity(
                id = 2,
                costPerLaunch = 1,
                firstFlight = "22-01-1998",
                height = 100,
                mass = 10000
            )
        )

        runBlocking {
            satelliteDetailDao.insertAll(satelliteDetailList)
            val satelliteName = satelliteDetailDao.getSatelliteDetail(5)
            assert(satelliteName.firstOrNull() == null)
        }
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}