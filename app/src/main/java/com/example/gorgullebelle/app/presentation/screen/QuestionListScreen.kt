package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.QuestionItemList
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel

data class ExerciseItem(val title: String, val imageResId: Int, val concept: String, val route: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionListScreen(
    navigate: (String) -> Unit = {},
    questionViewModel: QuestionViewModel,
    profileViewModel: ProfileViewModel
) {
    val initialExerciseItems = listOf(
        ExerciseItem("Konu tespiti", R.drawable.konu_tespiti,  "konuTespiti", Route.QuestionScreen.route),
        ExerciseItem("Gereklilik tespiti", R.drawable.dogru_atama, "gereklilikTespiti", Route.QuestionScreen.route),
        ExerciseItem("Uygun eylem", R.drawable.dogru_eylem, "uygunEylem", Route.QuestionScreen.route)
    )

    var exerciseItems by rememberSaveable { mutableStateOf(initialExerciseItems) }

    val expanded = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                conversationTitle = "Kısıtlı anlama",
                onBackPressed = { navigate(Route.DashboardScreen.route) },
                menuContent = {
                    IconButton(onClick = { expanded.value = !expanded.value }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Konular") },
                            onClick = {
                                navigate(Route.AddTopicScreen.route)
                                expanded.value = false
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->


        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            QuestionItemList(
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
