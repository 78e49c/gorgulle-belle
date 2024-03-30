package com.example.gorgullebelle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gorgullebelle.app.App
import com.example.gorgullebelle.ui.theme.SalihTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalihTheme {
                App()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SalihTheme {
        App()
    }
}