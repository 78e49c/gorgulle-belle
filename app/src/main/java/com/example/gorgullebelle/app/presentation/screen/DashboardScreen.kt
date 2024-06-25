package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.data.dataclass.BottomNavItem
import com.example.gorgullebelle.app.data.dataclass.CarouselItem
import com.example.gorgullebelle.app.presentation.components.BottomNavigationBar
import com.example.gorgullebelle.app.presentation.components.MultiCarousel
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    chatManagerViewModel: ChatManagerViewModel,
    questionViewModel: QuestionViewModel,
    navigate: (String) -> Unit = {}
) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem("Kimlik", ImageVector.vectorResource(id = R.drawable.sharp_person_24), selectedItem == 0),
        BottomNavItem("Konuşma", Icons.Filled.Email, selectedItem == 1),
        BottomNavItem("Seçim", Icons.Filled.CheckCircle, selectedItem == 2)
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = items,
                onItemClick = { item ->
                    selectedItem = items.indexOf(item)
                    when (item.label) {
                        "Kimlik" -> navigate(Route.ProfileScreen.route)
                        "Konuşma" -> navigate(Route.ConversationListScreen.route)
                        "Seçim" -> navigate(Route.QuestionListScreen.route)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.dashboard_background_color))
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                val itemsCarousel1 = listOf(
                    CarouselItem(
                        id = 1,
                        imageRes = R.drawable.ai_dedektifi,
                        title = "AI Dedektifi",
                        body = "İnkarcı AI'ın, AI olduğuna dair argümanlarını ona kabul ettir",
                        buttonText = "Konuşmaya git"
                    ),
                    CarouselItem(
                        id = 2,
                        imageRes = R.drawable.gonul_hasbihali,
                        title = "Gönül Hasbihali",
                        body = "Sana olumlu yaklaşan birine, kibar romantik bir konuşma ile kendini biraz daha beğendir",
                        buttonText = "Konuşmaya git"
                    ),
                    CarouselItem(
                        id = 3,
                        imageRes = R.drawable.sam_yolculugu,
                        title = "Şam Yolculuğu",
                        body = "Arkadaşını Şam'a gitmeye ikna et ",
                        buttonText = "Konuşmaya git"
                    )
                )

                val itemsCarousel2 = listOf(
                    CarouselItem(
                        id = 4,
                        imageRes = R.drawable.konu_tespiti,
                        title = "Konu tespiti",
                        body = "En azından konuyu anlayabilirsin",
                        buttonText = "Sorulara git"
                    ),
                    CarouselItem(
                        id = 5,
                        imageRes = R.drawable.dogru_atama,
                        title = "Gereklilik tespiti",
                        body = "Anladığın kadarıyla çözüm bul",
                        buttonText = "Sorulara git"
                    ),
                    CarouselItem(
                        id = 6,
                        imageRes = R.drawable.dogru_eylem,
                        title = "Uygun eylem",
                        body = "Sadece yapılması gerekeni yap",
                        buttonText = "Sorulara git"
                    )
                )

                val carousels = listOf(
                    "Konuşmalar" to itemsCarousel1,
                    "Seçimler" to itemsCarousel2
                )

                MultiCarousel(
                    carousels = carousels,
                    onTitleClick = { title ->
                        when (title) {
                            "Konuşmalar" -> {
                                navigate(Route.ConversationListScreen.route)
                            }
                            "Seçimler" -> {
                                navigate(Route.QuestionListScreen.route)
                            }
                        }
                    },
                    onButtonClick = { itemId ->
                        when (itemId) {
                            1 -> {
                                chatManagerViewModel.setSelectedPackageIndex(0)
                                navigate(Route.ConversationScreen.route)
                            }
                            2 -> {
                                chatManagerViewModel.setSelectedPackageIndex(1)
                                navigate(Route.ConversationScreen.route)
                            }
                            3 -> {
                                chatManagerViewModel.setSelectedPackageIndex(2)
                                navigate(Route.ConversationScreen.route)
                            }
                            4 -> {
                                questionViewModel.setConcept("konuTespiti")
                                navigate(Route.QuestionScreen.route)
                            }
                            5 -> {
                                questionViewModel.setConcept("gereklilikTespiti")
                                navigate(Route.QuestionScreen.route)
                            }
                            6 -> {
                                questionViewModel.setConcept("uygunEylem")
                                navigate(Route.QuestionScreen.route)
                            }
                        }
                    }
                )
            }
        }
    }
}


