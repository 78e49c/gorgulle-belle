package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.IconComponent
import com.example.gorgullebelle.app.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
    navigate: (String) -> Unit = {},
) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Kısıtlı anlama",
                onIconClick = { navigate(Route.DashboardScreen.route) }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            IconComponent(onClick = {navigate(Route.ExerciseScreen.route)},
                painterResource = painterResource(id = R.drawable.baseline_email_24)
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // İçerik buraya gelecek
            }
        }
    }
}