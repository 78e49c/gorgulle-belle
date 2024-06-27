package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.ProfileRow
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navigate: (String) -> Unit = {},
    profileViewModel: ProfileViewModel,
    userViewModel: UserViewModel
) {

    val onlineUser by userViewModel.onlineUser.collectAsState()
    val selectedTopic by profileViewModel.selectedTopic.observeAsState(0)
    var expanded by remember { mutableStateOf(false) }

    val swipeableState = rememberSwipeableState(initialValue = 0)
    val scope = rememberCoroutineScope()
    val anchors = mapOf(0f to 0, -300f to 1) // Adjust the swipe distance as needed


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
                CustomTopAppBar(
                    conversationTitle = "Kimlik",
                    onBackPressed = { navigate(Route.DashboardScreen.route) },
                    menuContent = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.out)) },
                                onClick = {
                                    userViewModel.signOut()
                                    navigate(Route.SignInScreen.route)
                                    expanded = false
                                }
                            )
                        }
                    }
                )
            }
        ){ values ->
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

                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = onlineUser.name +" "+ onlineUser.surname,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 30.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(1.dp))

                    ProfileRow(
                        title = "Email",
                        value = onlineUser.email
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
                        title = "Seçili Konu",
                        value = selectedTopic.toString()
                    )

                    ProfileRow(
                        title = "Puan",
                        value = onlineUser.score.toString()
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp)
                ) {

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
