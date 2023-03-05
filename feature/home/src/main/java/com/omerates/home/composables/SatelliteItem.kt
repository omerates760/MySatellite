package com.omerates.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omerates.home.SatelliteCurrentType
import com.omerates.home.SatelliteUiModel

@Composable
fun SatelliteItem(
    satellite: SatelliteUiModel,
    onSatelliteClicked: (SatelliteUiModel) -> Unit
) {
    val backgroundColor = when (satellite.type) {
        SatelliteCurrentType.ACTIVE -> Color.Green
        SatelliteCurrentType.PASSIVE -> Color.Red
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .clickable {
                onSatelliteClicked(satellite)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor)
                .padding(
                    vertical = 20.dp,
                    horizontal = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = satellite.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = satellite.type.readableName,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewSatelliteItem() {
    SatelliteItem(
        satellite = SatelliteUiModel(
            1, "Satellite",
            SatelliteCurrentType.ACTIVE
        ),
        onSatelliteClicked = {}
    )
}