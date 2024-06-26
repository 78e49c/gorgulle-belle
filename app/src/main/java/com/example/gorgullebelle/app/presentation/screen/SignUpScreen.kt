package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ButtonComponent
import com.example.gorgullebelle.app.presentation.components.CheckboxCompoment
import com.example.gorgullebelle.app.presentation.components.ClickableLoginTextComponent
import com.example.gorgullebelle.app.presentation.components.HeadingTextComponent
import com.example.gorgullebelle.app.presentation.components.MyTextField
import com.example.gorgullebelle.app.presentation.components.NormalTextComponent
import com.example.gorgullebelle.app.presentation.components.PasswordTextField
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.UserViewModel

@Composable
fun SignUpScreen(
    userViewModel: UserViewModel,
    navigate: (String) -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
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
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.sign_up))

            Spacer(modifier = Modifier
                .alpha(0.0f)
                .height(20.dp))

            Column(Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                MyTextField(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.sharp_person_24),
                    onValueChange = { name = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.sharp_person_24),
                    onValueChange = { surname = it }
                )

                Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent))

                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.baseline_email_24),
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.baseline_key_24),
                    onValueChange = { password = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                CheckboxCompoment(value = stringResource(id = R.string.policy))

                Spacer(modifier = Modifier.weight(1f))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onClick = {
                        userViewModel.signUp(name, surname, email, password)
                        navigate.invoke(Route.SignInScreen.route)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ClickableLoginTextComponent(navigate = navigate, route = Route.SignInScreen.route, "Giriş yap ", "Hesabın varsa ")

                Spacer(modifier = Modifier.height(16.dp))

                ClickableLoginTextComponent(navigate = navigate, route = Route.DashboardScreen.route, "Geri dön ", "",)
            }
        }
    }
}


