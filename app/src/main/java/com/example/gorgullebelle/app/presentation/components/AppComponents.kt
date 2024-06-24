package com.example.gorgullebelle.app.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.data.dataclass.BottomNavItem
import com.example.gorgullebelle.app.data.dataclass.CarouselItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    onIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    onClick = onIconClick,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                Text(
                    text = title,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        /*
        backgroundColor = Color.Blue,
        contentColor = Color.Black,
        elevation = 4.dp

         */
    )
}







@Composable
fun CardItem(
    imageRes: Int,
    title: String,
    body: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp,(colorResource(id = R.color.card_border_color)),shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.card_color))
                .padding(16.dp)
                .width(232.dp)
                .height(420.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp),
                color = colorResource(id = R.color.dashboard_text_color)
            )
            Text(
                text = body,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp),
                color = colorResource(id = R.color.dashboard_text_color)
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    //.border(2.dp,Color.Black)
            ) {
                Text(
                    text = buttonText,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.dashboard_text_color)
                )
            }
        }
    }
}


@Composable
fun Carousel(
    title: String,
    items: List<CarouselItem>,
    onTitleClick: () -> Unit,
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.dashboard_text_color),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { onTitleClick() }
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
              //  .height(480.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                val item = items[index]
                CardItem(
                    imageRes = item.imageRes,
                    title = item.title,
                    body = item.body,
                    buttonText = item.buttonText,
                    onButtonClick = { onButtonClick(item.id) },
                    modifier = Modifier
                        .width(240.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}




@Composable
fun MultiCarousel(
    carousels: List<Pair<String, List<CarouselItem>>>,
    onTitleClick: (String) -> Unit,
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(carousels.size) { index ->
            val (title, items) = carousels[index]
            Carousel(
                title = title,
                items = items,
                onTitleClick = { onTitleClick(title) },
                onButtonClick = onButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}






@Composable
fun IconComponent(
    onClick: () -> Unit,
    painterResource: Painter
) {
    Image(
        painter = painterResource,
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .clickable { onClick() }
    )
}







@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    BottomNavigation(
        modifier = modifier,
        elevation = 8.dp,
        backgroundColor = (colorResource(id = R.color.bottom_navigation_color))
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = item.isSelected,
                onClick = { onItemClick(item) }
            )
        }
    }
}




