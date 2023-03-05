package com.omerates.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omerates.home.SatelliteCurrentType
import com.omerates.home.SatelliteUiModel


@Composable
fun SatelliteList(
    modifier: Modifier,
    satellites: List<SatelliteUiModel>,
    onSatelliteClicked: (SatelliteUiModel) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(satellites, key = { it.id }) { satellite ->
            SatelliteItem(satellite, onSatelliteClicked)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSatelliteList() {
    SatelliteList(
        modifier = Modifier,
        satellites = listOf(
            SatelliteUiModel(1, "Satellite-1", SatelliteCurrentType.ACTIVE),
            SatelliteUiModel(2, "Satellite-2", SatelliteCurrentType.PASSIVE),
            SatelliteUiModel(3, "Satellite-3", SatelliteCurrentType.ACTIVE)

        ),
        onSatelliteClicked = {})
}