package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.BottomNavItem
import com.example.gorgullebelle.app.presentation.components.BottomNavigationBar
import com.example.gorgullebelle.app.presentation.components.CarouselItem
import com.example.gorgullebelle.app.presentation.components.MultiCarousel
import com.example.gorgullebelle.app.presentation.components.gradientBackground3
import com.example.gorgullebelle.app.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navigate: (String) -> Unit = {}) {
    //val scaffoldState = rememberScaffoldState()
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem("Kimlik", ImageVector.vectorResource(id = R.drawable.sharp_person_24), selectedItem == 0),
        BottomNavItem("Konuşma", Icons.Filled.Email , selectedItem == 1),
        BottomNavItem("Seçim", Icons.Filled.CheckCircle, selectedItem == 2)
    )

    Scaffold(
       // scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                items = items,
                onItemClick = { item ->
                    selectedItem = items.indexOf(item)
                    when (item.label) {
                        "Kimlik" -> navigate(Route.ProfileScreen.route)
                        "Konuşma" -> navigate(Route.ConversationListScreen.route)
                        "Seçim" -> navigate(Route.ExerciseListScreen.route)
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
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .gradientBackground3()
                    .alpha(0.1f)
            ) {}



            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                                Log.d("Carousel", "Carousel 1 title clicked")
                                navigate(Route.ConversationListScreen.route)
                            }
                            "Seçimler" -> {
                                Log.d("Carousel", "Carousel 2 title clicked")
                                navigate(Route.ExerciseListScreen.route)
                            }
                        }
                    },
                    onButtonClick = { itemId ->
                        Log.d("Carousel", "Button in item $itemId clicked")
                        // Add navigation or other action here
                    }
                )
            }
        }
    }
}
