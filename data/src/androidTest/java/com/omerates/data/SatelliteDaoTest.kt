package com.omerates.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.omerates.data.model.entity.SatelliteEntity
import com.omerates.data.source.local.dao.SatelliteDao
import com.omerates.data.source.local.database.SatelliteDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
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
class SatelliteDaoTest {
    private lateinit var database: SatelliteDatabase
    private lateinit var satelliteDao: SatelliteDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SatelliteDatabase::class.java
        ).allowMainThreadQueries().build()

        satelliteDao = database.SatelliteDao()
    }

    @Test
    fun getAllSatellite_whenInsertSatelliteReturnGetSatelliteSize() {
        val fakeList = mutableListOf<SatelliteEntity>().apply {
            add(
                SatelliteEntity(
                    id = 1,
                    name = "Omer",
                    active = false
                )
            )
        }
        runBlocking {
            satelliteDao.insertAll(fakeList)
            val allData = satelliteDao.getAllSatellite()
            assertThat(allData.first().size, Is.`is`(1))
        }
    }

    @Test
    fun getSatelliteNameById_whenSendIdReturnSatelliteName() {
        val satellite = SatelliteEntity(
            id = 1,
            name = "Omer",
            active = false
        )

        runBlocking {
            satelliteDao.insert(satellite)
            val satelliteName = satelliteDao.getSatelliteNameById(1)
            assertThat(satelliteName.first(), Is.`is`("Omer"))
        }
    }

    @Test
    fun searchSatellite_whenSearchNameReturnCheckedSatelliteSize() {
        val satellite = listOf(
            SatelliteEntity(
                id = 1,
                name = "Alev",
                active = false
            ),
            SatelliteEntity(
                id = 2,
                name = "Ali",
                active = false
            ),
            SatelliteEntity(
                id = 3,
                name = "Ağrı",
                active = false
            )
        )

        runBlocking {
            satelliteDao.insertAll(satellite)
            val satelliteName = satelliteDao.searchSatellite("Al")
            assertThat(satelliteName.first().size, Is.`is`(2))
        }
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}