package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.Carousel
import com.example.gorgullebelle.app.presentation.components.HeadingTextComponent
import com.example.gorgullebelle.app.presentation.components.IconComponent
import com.example.gorgullebelle.app.presentation.components.gradientBackground
import com.example.gorgullebelle.app.presentation.navigation.Route

@Composable
fun DashboardScreen(navigate: (String) -> Unit = {}) {


    Surface(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .gradientBackground()
                .alpha(0.1f)
        ) {}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        )
        {

            IconComponent(onClick = {navigate(Route.ProfileScreen.route)},
                painterResource = painterResource(id = R.drawable.sharp_person_24))

            HeadingTextComponent(value = stringResource(id = R.string.app_name))



            Carousel(
                title = "Carousel 1",
                imageResources = listOf(R.drawable.ai_dedektifi, R.drawable.gonul_hasbihali, R.drawable.sam_yolculugu),
                onTitleClick = {
                    Log.d("Carousel", "Carousel 1 title clicked")
                    navigate.invoke(Route.ConversationListScreen.route)
                },
                onImageClick = { imageRes ->
                    when (imageRes) {
                        R.drawable.ai_dedektifi -> Log.d("Carousel", "Image 1 clicked")
                        R.drawable.gonul_hasbihali -> Log.d("Carousel", "Image 2 clicked")
                        R.drawable.sam_yolculugu -> Log.d("Carousel", "Image 3 clicked")
                    }
                }
            )

            Carousel(
                title = "Carousel 2",
                imageResources = listOf(R.drawable.default_image, R.drawable.default_image, R.drawable.default_image),
                onTitleClick = {
                    Log.d("Carousel", "Carousel 2 title clicked")
                    navigate.invoke(Route.ExerciseListScreen.route)
                },
                onImageClick = { imageRes ->
                    when (imageRes) {
                        R.drawable.default_image -> Log.d("Carousel", "Image 4 clicked")
                        R.drawable.default_image -> Log.d("Carousel", "Image 5 clicked")
                        R.drawable.default_image -> Log.d("Carousel", "Image 6 clicked")
                    }
                }
            )




        }
    }
}











