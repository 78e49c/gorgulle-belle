package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ButtonComponent
import com.example.gorgullebelle.app.presentation.components.CheckboxCompoment
import com.example.gorgullebelle.app.presentation.components.ClickableLoginTextComponent
import com.example.gorgullebelle.app.presentation.components.HeadingTextComponent
import com.example.gorgullebelle.app.presentation.components.MyTextField
import com.example.gorgullebelle.app.presentation.components.NormalTextComponent
import com.example.gorgullebelle.app.presentation.components.PasswordTextField
import com.example.gorgullebelle.app.presentation.components.gradientBackground1
import com.example.gorgullebelle.app.presentation.navigation.Route


@Composable
fun SignUpScreen(navigate: (String) -> Unit = {}) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .gradientBackground1()
                .alpha(0.1f)
        ) {}

        Column(
            Modifier
                .fillMaxSize()
                .padding(28.dp)

        ) {


            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id =  R.string.sign_up))

            Spacer(modifier = Modifier
                .alpha(0.0f)
                .height(20.dp))

            Column(Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                MyTextField(
                    labelValue = stringResource(id = R.string.first_name)
                    , painterResource(id = R.drawable.sharp_person_24)
                )

                Spacer(modifier = Modifier.height(10.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.last_name)
                    , painterResource(id = R.drawable.sharp_person_24)

                )
                Spacer(modifier = Modifier
                    .height(10.dp)
                    .background(Color.Transparent))

                MyTextField(
                    labelValue = stringResource(id = R.string.email)
                    , painterResource(id = R.drawable.baseline_email_24)
                )

                Spacer(modifier = Modifier
                    .height(10.dp)
                    .background(Color.Transparent))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password)
                    , painterResource(id = R.drawable.baseline_key_24)
                )
                CheckboxCompoment(value = stringResource(id = R.string.policy))

                Spacer(modifier = Modifier.weight(1f))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onClick = {
                        navigate.invoke(Route.SignInScreen.route)
                    }
                )

                ClickableLoginTextComponent(navigate = navigate, route = Route.SignInScreen.route, "Giriş yap " ,"Hesabın varsa ")
                ClickableLoginTextComponent(navigate = navigate, route = Route.DashboardScreen.route," Geri dön ","",)
            }



        }



    }
}








@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}