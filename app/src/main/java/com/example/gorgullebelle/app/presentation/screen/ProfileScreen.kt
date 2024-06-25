package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ClickableLoginTextComponent
import com.example.gorgullebelle.app.presentation.components.ProfileRow
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navigate: (String) -> Unit = {},
    profileViewModel: ProfileViewModel = viewModel()
) {
    val score by profileViewModel.score.observeAsState(0)
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val scope = rememberCoroutineScope()
    val anchors = mapOf(0f to 0, -300f to 1) // Adjust the swipe distance as needed
    val expanded = remember { mutableStateOf(false) }

    Surface(modifier = Modifier
        .fillMaxSize()
        .swipeable(
            state = swipeableState,
            anchors = anchors,
            thresholds = { _, _ -> FractionalThreshold(0.3f) },
            orientation = Orientation.Horizontal
        )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Kimlik") },
                    navigationIcon = {
                        IconButton(onClick = { navigate(Route.DashboardScreen.route) }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Geri Dön")
                        }
                    },
                    actions = {
                        IconButton(onClick = { expanded.value = !expanded.value }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Konuları Düzenle") },
                                onClick = {
                                    navigate(Route.AddTopicScreen.route)
                                    expanded.value = false
                                }
                            )
                        }
                    }
                )
            }
        ) { values ->
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.sharp_person_24),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(180.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(90.dp)),
                        contentDescription = ""
                    )

                    Text(
                        text = "Salih Durak",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 30.sp
                        )
                    )

                    ProfileRow(
                        title = "Bildiği dil",
                        value = "Türkçe"
                    )

                    ProfileRow(
                        title = "Öğrendiği dil",
                        value = "İngilizce"
                    )

                    ProfileRow(
                        title = "Puan",
                        value = score.toString()
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp)
                ) {
                    ClickableLoginTextComponent(
                        navigate = navigate,
                        route = Route.SignInScreen.route,
                        stringResource(id = R.string.out),
                        ""
                    )
                }
            }
        }
    }

    LaunchedEffect(swipeableState.currentValue) {
        if (swipeableState.currentValue == 1) {
            navigate(Route.DashboardScreen.route)
            scope.launch {
                swipeableState.snapTo(0)
            }
        }
    }
}
