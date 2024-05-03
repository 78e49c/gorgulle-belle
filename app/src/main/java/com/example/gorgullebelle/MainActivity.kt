package com.example.gorgullebelle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gorgullebelle.app.App
import com.example.gorgullebelle.screens.SignUpScreen
import com.example.gorgullebelle.ui.theme.GBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GBTheme {
                SignUpScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GBTheme {
        SignUpScreen()
    }
}