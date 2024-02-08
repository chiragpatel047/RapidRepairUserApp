package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.textBetweenTwoLines
import com.chirag047.rapidrepair.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

        val scroll = rememberScrollState()

        Column(modifier = Modifier.verticalScroll(scroll)) {
            Spacer(modifier = Modifier.padding(50.dp))

            poppinsBoldCenterText(
                contentText = "Your Seamless Repair App",
                22.sp,
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            textBetweenTwoLines(text = "Register")

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var nameText by remember { mutableStateOf("") }
                var emailText by remember { mutableStateOf("") }
                var passwordText by remember { mutableStateOf("") }

                TextField(
                    value = nameText,
                    onValueChange = { nameText = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Username",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 20.dp, 20.dp, 5.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))

                )

                TextField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Email",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 20.dp, 20.dp, 5.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))

                )

                TextField(
                    value = passwordText,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = { passwordText = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Password",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 20.dp, 20.dp, 5.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))

                )


            }

            Spacer(modifier = Modifier.padding(40.dp))

            FullWidthButton(label = "Continue", MaterialTheme.colorScheme.primary) {
                navController.navigate("otp")
            }


        }
    }
}

