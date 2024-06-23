package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.ExerciseItemList
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel

data class ExerciseItem(val title: String, val imageResId: Int, val body: String, val concept: String, val route: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionListScreen(
    navigate: (String) -> Unit = {},
    questionViewModel: QuestionViewModel
) {
    val initialExerciseItems = listOf(
        ExerciseItem("Konu tespiti", R.drawable.konu_tespiti, "0", "konuTespiti", Route.ExerciseScreen.route),
        ExerciseItem("Gereklilik tespiti", R.drawable.dogru_atama, "0", "gereklilikTespiti", Route.ExerciseScreen.route),
        ExerciseItem("Uygun eylem", R.drawable.dogru_eylem, "0", "uygunEylem", Route.ExerciseScreen.route)
    )

    var exerciseItems by rememberSaveable { mutableStateOf(initialExerciseItems) }

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
            ExerciseItemList(
                exerciseItems = exerciseItems,
                onItemClick = { item ->
                    questionViewModel.setConcept(item.concept)
                    // Move the clicked item to the top of the list
                    exerciseItems = listOf(item) + exerciseItems.filter { it != item }
                    navigate(item.route)
                }
            )
        }
    }
}
