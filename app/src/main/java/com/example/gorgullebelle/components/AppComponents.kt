package com.example.gorgullebelle.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.navigation.Route


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
            .clip(RoundedCornerShape(8.dp))
            //.background(colorResource(id = R.color.over_transparent_background))
                ,

        label = {
            Text(
                text = labelValue,
                modifier = Modifier
                    //.background(colorResource(id = R.color.transparent_background))
            )
        },



        colors = TextFieldDefaults

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
            .clip(RoundedCornerShape(8.dp))
           // .background(colorResource(id = R.color.over_transparent_background))
                ,

        label = { Text(text = labelValue) },

        colors = TextFieldDefaults

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
fun ButtonComponent(value: String){
    Button(
        onClick = { /*TODO*/ },
        modifier =
        Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)

    ) {

        Box( modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.white),
                        colorResource(id = R.color.grad_3)
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
            )
        {
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
//onTextSelected: (String) -> Unit
fun ClickableLoginTextComponent(){
    val text1 = "Already have an account? "
    val text2 = "Login "


    val annotatedString = buildAnnotatedString {
        append(text1)

        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_700))){
            pushStringAnnotation(tag = text2, annotation = text2)
            append(text2)
        }


    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),

        text = annotatedString,
        onClick ={offset ->

        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent","{$span}")
                if(span.item == text2){
                   // navigate(Route.SignUpScreen.route)
                }
            }
    }
    )
}







