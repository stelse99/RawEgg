package com.example.rawegg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.rawegg.ui.theme.RawEggTheme
import com.example.rawegg.views.BottomNavigationScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        const val TAG: String = "로그"
    }

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RawEggTheme {
                ContentView()
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun ContentView() {
    // A surface container using the 'background' color from the theme
    Surface (
        color = MaterialTheme.colors.background
    ) {
        BottomNavigationScreen()
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RawEggTheme {
        ContentView()
    }
}
