package com.example.gorgullebelle.app.presentation.components


//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value


// If you're using vector drawables for icons


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.domain.model.Question


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
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
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
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = body,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = buttonText,
                    fontSize = 14.sp,
                    color = Color.Black)
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

data class CarouselItem(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val body: String,
    val buttonText: String
)



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
        backgroundColor = Color.LightGray
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


data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val isSelected: Boolean = false
)



@Composable
fun Modifier.gradientBackground1(): Modifier = this
    .background(
        brush = Brush.linearGradient(
            colors =  listOf(
                colorResource(id = R.color.grad_3),
                colorResource(id = R.color.grad_2),
                colorResource(id = R.color.grad_1),
                colorResource(id = R.color.grad_0)
            )
        )
    )

@Composable
fun Modifier.gradientBackground2(): Modifier = this
    .background(
        brush = Brush.linearGradient(
            colors =  listOf(
                colorResource(id = R.color.grad_0),
                colorResource(id = R.color.grad_3),
                colorResource(id = R.color.grad_2),
                colorResource(id = R.color.grad_1)
            )
        )
    )



@Composable
fun Modifier.gradientBackground3(): Modifier = this
    .background(
        brush = Brush.linearGradient(
            colors =  listOf(
                colorResource(id = R.color.grad_1),
                colorResource(id = R.color.grad_0),
                colorResource(id = R.color.grad_3),
                colorResource(id = R.color.grad_2)
            )
        )
    )

@Composable
fun Modifier.gradientBackground4(): Modifier = this
    .background(
        brush = Brush.linearGradient(
            colors =  listOf(
                colorResource(id = R.color.grad_2),
                colorResource(id = R.color.grad_1),
                colorResource(id = R.color.grad_0),
                colorResource(id = R.color.grad_3)
            )
        )
    )

