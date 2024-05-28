package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
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
                        ExperienceListItem(
                            title = "Konu tespiti",
                            imageResId = R.drawable.konu_tespiti,

                            onClick = {
                                navigate(Route.ExerciseScreen.route)
                            }
                        )
                    }

                    item {
                        ExperienceListItem(
                            title = "Gereklilik tespiti",
                            imageResId = R.drawable.dogru_atama,

                            onClick = {


                            }
                        )
                    }

                    item {
                        ExperienceListItem(
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



@Composable
fun ExperienceListItem(title: String, imageResId: Int, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp))
           // Spacer(modifier = Modifier.height(4.dp))
           // Text(text = "asd", style = MaterialTheme.typography.bodyMedium)
        }
    }
}