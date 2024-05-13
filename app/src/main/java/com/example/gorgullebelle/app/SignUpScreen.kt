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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.components.CheckboxCompoment
import com.example.gorgullebelle.components.HeadingTextComponent
import com.example.gorgullebelle.components.MyTextField
import com.example.gorgullebelle.components.NormalTextComponent
import com.example.gorgullebelle.components.PasswordTextField


@Composable
fun SignUpScreen(navigate: (String) -> Unit = {}) {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    )
    {
        Column(Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

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
                Spacer(modifier = Modifier.height(10.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.email)
                    , painterResource(id = R.drawable.baseline_email_24)
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextField(
                    labelValue = stringResource(id = R.string.password)
                    , painterResource(id = R.drawable.baseline_key_24)
                )
                CheckboxCompoment(value = stringResource(id = R.string.policy))
            }


        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}