package com.omerates.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omerates.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    searchPlaceholder: String,
    onDeletePressed: () -> Unit,
    onValueChanged: (String) -> Unit,
    onSearch: () -> Unit
) {

    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = query,
            onValueChange = onValueChanged,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp,
                lineHeight = 10.sp,
                letterSpacing = (-0.1).sp
            ),
            placeholder = {
                Text(
                    text = searchPlaceholder,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontSize = 15.sp,
                        color = Color.Black
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            trailingIcon = {
                if (!(query.isEmpty() || query.isBlank())) {
                    IconButton(onClick = onDeletePressed) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Delete Button",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Delete Button",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            )
        )
    }
}

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar(
        query = "Dragon",
        searchPlaceholder = "Example",
        onDeletePressed = { },
        onValueChanged = {}
    ) {}
}