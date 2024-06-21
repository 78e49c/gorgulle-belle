package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.ExerciseCardItem
import com.example.gorgullebelle.app.presentation.navigation.Route

data class ExerciseItem(val title: String, val imageResId: Int, val body: String, val route: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(navigate: (String) -> Unit = {}) {
    val exerciseItems = listOf(
        ExerciseItem("Konu tespiti", R.drawable.konu_tespiti, "0/20", Route.ExerciseScreen.route),
        ExerciseItem("Gereklilik tespiti", R.drawable.dogru_atama, "3/74", Route.ExerciseScreen.route),
        ExerciseItem("Uygun eylem", R.drawable.dogru_eylem, "25/35", Route.ExerciseScreen.route)
    )

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
            LazyColumn(
                modifier = Modifier
                    //.fillMaxSize()
                    .padding(8.dp)
            ) {
                items(exerciseItems.chunked(2)) { rowItems ->
                    Row(modifier = Modifier.padding()) {
                        for (item in rowItems) {
                            ExerciseCardItem(
                                imageRes = item.imageResId,
                                title = item.title,
                                body = item.body,
                                onButtonClick = { navigate(item.route) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}
