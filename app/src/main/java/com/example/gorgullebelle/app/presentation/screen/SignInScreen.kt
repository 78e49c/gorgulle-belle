package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.*
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.UserViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable





@Composable
fun SignInScreen(
    userViewModel: UserViewModel,
    navigate: (String) -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            NormalTextComponent(value = stringResource(id = R.string.welcome))
            HeadingTextComponent(value = stringResource(id = R.string.sign_in))

            Spacer(modifier = Modifier.alpha(0.0f).height(20.dp))

            Column(Modifier.wrapContentSize()) {
                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.baseline_email_24),
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.baseline_key_24),
                    onValueChange = { password = it }
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onClick = {
                        val user = userViewModel.signIn(email, password)
                        if (user != null) {
                            println("Name: ${user.first}, Surname: ${user.second}")
                            navigate.invoke(Route.ProfileScreen.route)
                        } else {
                            println("Invalid email or password")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ClickableLoginTextComponent(navigate = navigate, route = Route.SignUpScreen.route, "Hesap oluştur ", "Hesabın yoksa ")

                Spacer(modifier = Modifier.height(16.dp))

                ClickableLoginTextComponent(navigate = navigate, route = Route.DashboardScreen.route, "Geri dön ", "",)
            }
        }
    }
}


