package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ButtonComponent
import com.example.gorgullebelle.app.presentation.components.ClickableLoginTextComponent
import com.example.gorgullebelle.app.presentation.components.HeadingTextComponent
import com.example.gorgullebelle.app.presentation.components.MyTextField
import com.example.gorgullebelle.app.presentation.components.NormalTextComponent
import com.example.gorgullebelle.app.presentation.components.PasswordTextField
import com.example.gorgullebelle.app.presentation.components.gradientBackground2
import com.example.gorgullebelle.app.presentation.navigation.Route


@Composable
fun SignInScreen(navigate: (String) -> Unit = {}) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .gradientBackground2()
                .alpha(0.1f)
        ) {}

        Column(
            Modifier
                .fillMaxSize()
                .padding(28.dp)

        ) {

            NormalTextComponent(value = stringResource(id = R.string.welcome))
            HeadingTextComponent(value = stringResource(id = R.string.sign_in))

            Spacer(modifier = Modifier
                .alpha(0.0f)
                .height(20.dp))

            Column(Modifier.wrapContentSize()) {

                MyTextField(
                    labelValue = stringResource(id = R.string.email)
                    , painterResource(id = R.drawable.baseline_email_24)
                )

                Spacer(modifier = Modifier
                    .height(10.dp))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password)
                    , painterResource(id = R.drawable.baseline_key_24)
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onClick = {
                        navigate.invoke(Route.ProfileScreen.route)
                    }
                )

                ClickableLoginTextComponent(navigate = navigate, route = Route.SignUpScreen.route," Hesap oluştur ","Hesabın yoksa ",)
                ClickableLoginTextComponent(navigate = navigate, route = Route.DashboardScreen.route," Geri dön ","",)

            }



        }


        }

    }




@Preview
@Composable
fun DefaultPreviewOfSignInScreen() {
    SignInScreen()
}

