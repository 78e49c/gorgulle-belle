package com.example.gorgullebelle.app.presentation.components


//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value


// If you're using vector drawables for icons


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.domain.model.Question

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue: String, painterResource: Painter) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            //.clip(RoundedCornerShape(8.dp))
            //.background(colorResource(id = R.color.over_transparent_background))
                ,

        label = {
            Text(
                text = labelValue,
                modifier = Modifier
                    //.background(colorResource(id = R.color.transparent_background))
            )
        },


        colors = TextFieldDefaults.tfc1()
        ,
        singleLine = true,


        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDefaults.tfc1(): TextFieldColors = this

    .textFieldColors(

        containerColor = colorResource(id = R.color.transparent_background),

        focusedLabelColor = colorResource(id = R.color.black),
        unfocusedLabelColor = colorResource(id = R.color.black),

        focusedSupportingTextColor = colorResource(id = R.color.black),
        unfocusedSupportingTextColor = colorResource(id = R.color.black),

        focusedLeadingIconColor = colorResource(id = R.color.black),
        unfocusedLeadingIconColor = colorResource(id = R.color.black),

        focusedTrailingIconColor = colorResource(id = R.color.black),
        unfocusedTrailingIconColor = colorResource(id = R.color.black),

        cursorColor = colorResource(id = R.color.black)
    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDefaults.tfc2(): TextFieldColors = this

    .outlinedTextFieldColors(

        unfocusedBorderColor = colorResource(id = R.color.transparent_background),
        focusedBorderColor = colorResource(id = R.color.transparent_background),

        focusedLabelColor = colorResource(id = R.color.black),
        unfocusedLabelColor = colorResource(id = R.color.black),

        focusedSupportingTextColor = colorResource(id = R.color.black),
        unfocusedSupportingTextColor = colorResource(id = R.color.black),

        focusedLeadingIconColor = colorResource(id = R.color.black),
        unfocusedLeadingIconColor = colorResource(id = R.color.black),

        focusedTrailingIconColor = colorResource(id = R.color.black),
        unfocusedTrailingIconColor = colorResource(id = R.color.black),

        cursorColor = colorResource(id = R.color.black),

        )





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String, painterResource: Painter) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            //.clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.half_transparent_background))
                ,

        label = { Text(text = labelValue) },

        colors = TextFieldDefaults.tfc1()

        ,
        singleLine = true,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password ),
        value = password.value,
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
                Icon(painter = painterResource,contentDescription = "")
        },

        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                painterResource(id = R.drawable.visibility_24px)

            } else {
                painterResource(id = R.drawable.visibility_off_24px)
            }


            var description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value})
            {
                Icon(painter = iconImage,contentDescription = description)
            }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None
        else PasswordVisualTransformation()
    )

}

@Composable
fun CheckboxCompoment(value: String){
    Row (modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp)
        ,verticalAlignment = Alignment.CenterVertically,
        ){
        val checkedState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value,
            onCheckedChange = {checkedState.value = !checkedState.value
            })

        ClickableTextComponent(value = value)
    }

}

@Composable
fun ClickableTextComponent(value: String){
    val text1 = "By continuing you agree to our "
    val text2 = "Terms of Service "
    val text3 = "and "
    val text4 = "Privacy Policy"

    val annotatedString = buildAnnotatedString {
        append(text1)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_700))){
            pushStringAnnotation(tag = text2, annotation = text2)
            append(text2)
        }
        append(text3)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_700))){
            pushStringAnnotation(tag = text4, annotation = text4)
            append(text4)
        }

    }
    ClickableText(text = annotatedString, onClick ={offset ->

     annotatedString.getStringAnnotations(offset,offset)
         .firstOrNull()?.also { span ->
             Log.d("ss","{$span}")
         }
 }
 )
}

@Composable
fun ButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            //.width(200.dp)
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.black),
                            colorResource(id = R.color.black)
                        )
                    ),
                    //shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
//onTextSelected: (String) -> Unit
fun ClickableLoginTextComponent(navigate: (String) -> Unit = {}, route: String, clickable: String, plain: String ){

    val annotatedString = buildAnnotatedString {
        append(plain)

        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_700))){
            pushStringAnnotation(tag = clickable, annotation = clickable)
            append(clickable)
        }


    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),

        text = annotatedString,
        onClick ={offset ->

        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent","{$span}")
                if(span.item == clickable){
                    Log.d("","nav")
                    navigate.invoke(route)
                }
            }
    }
    )
}




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
fun QuestionComponent(
    question: Question,
    selectedChoice: Int,
    onChoiceSelected: (Int, Int) -> Unit,
    onNextQuestion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = question.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        question.choices.forEachIndexed { index, choice ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onChoiceSelected(choice.score, index)
                    }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (selectedChoice == index),
                    onClick = {
                        onChoiceSelected(choice.score, index)
                    }
                )
                Text(text = choice.text, fontSize = 16.sp)
            }
        }

        // Button to move to the next question
        //Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.weight(0.2f))

        Row {
            Spacer(modifier = Modifier.weight(0.5f))
            Button( onClick = { onNextQuestion() }) {
                Text(text = "Sonraki soru")
            }
        }

    }
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

