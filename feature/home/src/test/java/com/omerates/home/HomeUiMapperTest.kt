package com.omerates.home
import com.omerates.domain.model.SatelliteDomainItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class HomeUiMapperTest {

    private val satelliteDomainItem = listOf(SatelliteDomainItem(active = false, 1, "Omer"))

    private lateinit var satelliteUiModel: List<SatelliteUiModel>

    @Before
    fun setup() {
        satelliteUiModel = satelliteDomainItem.toSatelliteListUiModel()
    }

    @Test
    fun id_whenSatelliteDomainItemMappedWithSatelliteUiModelId_check() {
        assertEquals(satelliteUiModel.first().id, satelliteDomainItem.first().id)
    }

    @Test
    fun size_whenSatelliteDomainItemMappedWithSatelliteUiModel_equal() {
        assertEquals(satelliteUiModel.size, satelliteDomainItem.size)
    }

}