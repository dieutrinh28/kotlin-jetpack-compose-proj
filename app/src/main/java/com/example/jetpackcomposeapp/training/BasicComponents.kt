package com.example.jetpackcomposeapp.training

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BasicComponents(name: String, modifier: Modifier) {
    Text(
        text = "Hello $name!",
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Magenta,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BasicComponentsPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        BasicComponents(
            name = "Jetpack Compose",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
