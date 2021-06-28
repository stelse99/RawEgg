package com.example.rawegg.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun TestScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        @Composable
        fun GradientButtonExample() {

            val horizontalGradientBrush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xffF57F17),
                    Color(0xffFFEE58),
                    Color(0xffFFF9C4)
                )
            )

            val verticalGradientBrush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xff4E342E),
                    Color(0xff8D6E63),
                    Color(0xffD7CCC8)
                )
            )

            Button(onClick = {}) {
                Text(
                    text = "Horizontal Gradient",
                    modifier = Modifier
                        .background(brush = horizontalGradientBrush)
                )
            }

            Button(onClick = {}) {
                Text(
                    text = "Vertical Gradient",
                    modifier = Modifier
                        .background(brush = verticalGradientBrush)
                )
            }
        }
    }
}