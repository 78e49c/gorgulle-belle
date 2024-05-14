package com.example.gorgullebelle.app

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.navigation.Route
import com.example.gorgullebelle.components.ButtonComponent
import com.example.gorgullebelle.components.CheckboxCompoment
import com.example.gorgullebelle.components.ClickableLoginTextComponent
import com.example.gorgullebelle.components.HeadingTextComponent
import com.example.gorgullebelle.components.MyTextField
import com.example.gorgullebelle.components.NormalTextComponent
import com.example.gorgullebelle.components.PasswordTextField
import com.example.gorgullebelle.components.gradientBackground


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
                .gradientBackground()
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

            Column(Modifier.wrapContentSize()) {
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

                Spacer(modifier = Modifier.height(160.dp))

                ButtonComponent(value = stringResource(id = R.string.register))

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