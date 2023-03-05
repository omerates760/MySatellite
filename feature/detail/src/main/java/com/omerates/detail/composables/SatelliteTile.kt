package com.omerates.detail.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SatelliteTile(style: TextStyle, value: String) {
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = value,
        style = style
    )
}

@Preview
@Composable
fun PreviewSatelliteTile() {
    SatelliteTile(style = TextStyle(fontSize = 20.sp), value = "Satellite Tile")
}