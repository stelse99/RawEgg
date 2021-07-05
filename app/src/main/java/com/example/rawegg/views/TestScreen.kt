package com.example.rawegg.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rawegg.ContentView
import com.example.rawegg.ui.theme.RawEggTheme

@Composable
fun GradientButton(
    text: String,
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}


@Composable
private fun Content() {

    val gradient =
        Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)))

    Column {
        GradientButton(
            text = "Gradient Button - Max Width",
            gradient = gradient,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        GradientButton(
            text = "Gradient Button - Wrap Width",
            gradient = gradient,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Click Me",
            style = TextStyle(color = Color.White),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable(onClick = {})
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Blue,
                            Color.Green
                        )
                    ),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = {}
        ) {
            Text(
                text = "Horizontal Gradient",
                modifier = Modifier
                    //.preferredHeight(ButtonDefaults.MinHeight)
                    .align(Alignment.CenterVertically)
                    .background(brush = gradient)
                    .padding(8.dp)
            )
        }

        SimpleButtonComponent()
        //SimpleTextComponent()
        SimpleCardComponent()
    }
}


@Composable
fun SimpleButtonComponent() {
    val context = LocalContext.current

    Column(
        Modifier
            .background(Color(0xFFEDEAE0))
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Button(
            onClick = {
                // do something here
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF2400),
                contentColor = Color(0xFFFFF5EE),
                disabledBackgroundColor = Color(0xFF59260B),
                disabledContentColor = Color(0xFF8A795D)
            ),
            enabled = true
        ) {
            Text(text = "Verify Me")
        }

        Button(
            onClick = {
                // do something here
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF2400),
                contentColor = Color(0xFFFFF5EE),
                disabledBackgroundColor = Color(0xFF59260B),
                disabledContentColor = Color(0xFF8A795D)
            ),
            enabled = false
        ) {
            Text(text = "Verify Me")
        }

        TextButton(
            onClick = {
                // do something here
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color(0xFFFAD6A5),
                contentColor = Color(0xFFA83731),
                disabledContentColor = Color(0xFFD2B48C)
            ),
            enabled = true
        ) {
            Text(text = "Share Me")
        }

        OutlinedButton(
            onClick = {
                // do something here
            },
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color(0xFF4C2882),
                contentColor = Color(0xFFECEBBD),
                disabledContentColor = Color(0xFF914E75)
            ),
            enabled = true
        ) {
            Text(text = "Share Location")
        }


        Button(
            onClick = {
                Toast.makeText(context, "Thanks for clicking! I am Button", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Click Me Now")
        }
    }
}

/*
@ExperimentalFoundationApi
@Composable
fun SimpleTextComponent() {
    val context = LocalContext.current
    Text(
        text = "Click Me",
        textAlign = TextAlign.Center,
        color = Color.Black,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .combinedClickable( onClick = {
                Toast.makeText(context, "Thanks for clicking! I am Text", Toast.LENGTH_SHORT).show()
            }, onLongClick = {
               Toast.makeText(context, "Thanks for LONG click! I am Text", Toast.LENGTH_SHORT).show()
            }, onDoubleClick = {
            Toast.makeText(context, "Thanks for DOUBLE click! I am Text", Toast.LENGTH_SHORT).show()
        })
    )
}
*/

@Composable
fun SimpleCardComponent() {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color(0xFFFFA867.toInt()),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                Toast.makeText(context, "Thanks for clicking! I am Card.", Toast.LENGTH_SHORT).show()
            })
    ) {
        Text(
            text = "Click Me",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}




@Composable
fun TestScreen() {
    Content()
}

@Composable
@Preview
fun DefaultPreview() {
    RawEggTheme {
        TestScreen()
    }
}
