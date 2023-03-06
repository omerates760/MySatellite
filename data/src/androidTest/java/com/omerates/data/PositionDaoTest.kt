package com.omerates.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.omerates.data.model.entity.PositionEntity
import com.omerates.data.source.local.dao.PositionDao
import com.omerates.data.source.local.database.SatelliteDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class PositionDaoTest {
    private lateinit var database: SatelliteDatabase
    private lateinit var positionDao: PositionDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SatelliteDatabase::class.java
        ).allowMainThreadQueries().build()

        positionDao = database.PositionDao()
    }

    @Test
    fun getSatelliteDetail_whenNotFoundSatelliteDetailByIdReturnTrue() {
        val position = PositionEntity(
            id = "1", 1, 0.2, 0.4
        )
        val satelliteDetailList = listOf(
            position
        )

        runBlocking {
            positionDao.insertAll(satelliteDetailList)
            val satelliteName = positionDao.getPositionsById(1)
            assert(satelliteName.firstOrNull()?.contains(position) == true)
        }
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}