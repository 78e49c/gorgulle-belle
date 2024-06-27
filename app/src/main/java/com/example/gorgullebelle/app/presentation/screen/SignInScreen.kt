package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ButtonComponent
import com.example.gorgullebelle.app.presentation.components.ClickableLoginTextComponent
import com.example.gorgullebelle.app.presentation.components.HeadingTextComponent
import com.example.gorgullebelle.app.presentation.components.MyTextField
import com.example.gorgullebelle.app.presentation.components.NormalTextComponent
import com.example.gorgullebelle.app.presentation.components.PasswordTextField
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@Composable
fun SignInScreen(
    userViewModel: UserViewModel,
    navigate: (String) -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(28.dp)
            ) {
                NormalTextComponent(value = stringResource(id = R.string.welcome))
                HeadingTextComponent(value = stringResource(id = R.string.sign_in))

                Spacer(modifier = Modifier.height(20.dp))

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
                            val message = validateSignIn(email, password, userViewModel)
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
                            }
                            if (message == "Giriş başarılı") {
                                userViewModel.signIn(email, password)
                                navigate(Route.ProfileScreen.route)
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
}

fun validateSignIn(email: String, password: String, userViewModel: UserViewModel): String {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return when {
        email.isEmpty() || password.isEmpty() -> "Boş alanları doldur"
        !Pattern.matches(emailPattern, email) -> "Geçerli bir email gir"
        password.length < 8 -> "Şifre en az 8 karakter olmalı"
        userViewModel.signIn(email, password) == null -> "Giriş başarısız"
        else -> "Giriş başarılı"
    }
}
