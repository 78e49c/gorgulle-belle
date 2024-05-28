package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ButtonComponent
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.ui.theme.GBTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigate: (String) -> Unit = {},
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                title = "Kimlik",
                onIconClick = { navigate(Route.DashboardScreen.route) }
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
                            .clip(RoundedCornerShape(90.dp)), contentDescription = ""

                    )

/*
                 AsyncImage(
                     model = user?.picture,
                     contentDescription = "Profile Image",
                     contentScale = ContentScale.FillBounds,
                     modifier = Modifier
                         .padding(top = 40.dp)
                         .width(180.dp)
                         .height(180.dp)
                         .clip(RoundedCornerShape(90.dp))
                 )

            */

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
                        value = "0"
                    )
             }

             Box(
                 modifier = Modifier
                     .align(Alignment.BottomCenter)
                     .padding(bottom = 40.dp)
             ) {
                 ButtonComponent(
                     value = stringResource(id = R.string.out),
                     onClick = {
                         navigate.invoke(Route.SignInScreen.route)
                     }
                 )
             }
         }
     }
 }
}

@Composable
fun ProfileRow(title: String, value: String) {
 Column(
     modifier = Modifier
         .fillMaxWidth()
         .padding(horizontal = 26.dp),
     verticalArrangement = Arrangement.spacedBy(8.dp)
 ) {
     Text(
         text = title,
         color = Color.Black,
         style = MaterialTheme.typography.titleMedium.copy(
             fontWeight = FontWeight(300),
             fontSize = 16.sp
         )
     )
     Spacer(modifier = Modifier
         .height(1.dp)
         .fillMaxWidth()
         .background(Color.LightGray))
     Text(
         text = value,
         color = Color.Black,
         style = MaterialTheme.typography.titleMedium.copy(
             fontWeight = FontWeight(500),
             fontSize = 16.sp
         )
     )
 }
}

@Preview
@Composable
fun ProfileScreenPreview() {
 GBTheme {
     ProfileScreen()
 }
}