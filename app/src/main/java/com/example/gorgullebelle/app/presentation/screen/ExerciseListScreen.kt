package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.ExerciseListItem
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

            Column(
                modifier = Modifier.fillMaxSize()
            ) {



                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    item {
                        ExerciseListItem(
                            title = "Konu tespiti",
                            imageResId = R.drawable.konu_tespiti,

                            onClick = {
                                navigate(Route.ExerciseScreen.route)
                            }
                        )
                    }

                    item {
                        ExerciseListItem(
                            title = "Gereklilik tespiti",
                            imageResId = R.drawable.dogru_atama,

                            onClick = {


                            }
                        )
                    }

                    item {
                        ExerciseListItem(
                            title = "Uygun eylem",
                            imageResId = R.drawable.dogru_eylem,
                            onClick = {

                            }
                        )
                    }
                }
            }





        }
        }
    }



