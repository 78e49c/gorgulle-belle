package com.example.gorgullebelle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gorgullebelle.navigation.NavGraph
import com.example.gorgullebelle.ui.theme.GBTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GBTheme {
                NavGraph()
            }
        }
    }
}



