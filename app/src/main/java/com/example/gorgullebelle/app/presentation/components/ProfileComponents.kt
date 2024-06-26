package com.example.gorgullebelle.app.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
fun MyTextField(labelValue: String, painterResource: Painter, onValueChange: (String) -> Unit) {
    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
        ,
        label = {
            Text(text = labelValue)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onValueChange(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String, painterResource: Painter, onValueChange: (String) -> Unit) {
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.half_transparent_background))
        ,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password.value,
        onValueChange = {
            password.value = it
            onValueChange(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                painterResource(id = R.drawable.visibility_24px)
            } else {
                painterResource(id = R.drawable.visibility_off_24px)
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(painter = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
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
    val text1 = "Devam ederek "
    val text2 = "Hizmet Şartlarımızı "
    val text3 = "ve "
    val text4 = "Gizlilik Politikamızı "
    val text5 = "kabul etmiş olursunuz."



    val annotatedString = buildAnnotatedString {
        append(text1)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.clickable_text_color))){
            pushStringAnnotation(tag = text2, annotation = text2)
            append(text2)
        }
        append(text3)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.clickable_text_color))){
            pushStringAnnotation(tag = text4, annotation = text4)
            append(text4)
        }
        append(text5)
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

        withStyle(style = SpanStyle(color = colorResource(id = R.color.clickable_text_color))){
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
