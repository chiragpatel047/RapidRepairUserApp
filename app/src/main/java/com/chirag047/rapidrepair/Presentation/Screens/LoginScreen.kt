package com.chirag047.rapidrepair.Presentation.Screens

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.textBetweenTwoLines
import com.chirag047.rapidrepair.Presentation.ViewModels.LoginViewModel
import com.chirag047.rapidrepair.R
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.chirag047.rapidrepair.Presentation.Components.customProgressBar as customProgressBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController,sharedPreferences: SharedPreferences) {

    val loginViewModel: LoginViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        Column() {
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                SubjectImage(
                    image = R.drawable.login_subject_image,
                    Modifier.padding(40.dp, 0.dp)
                )
            }

            poppinsBoldCenterText(
                contentText = "Hey Dear Welcome Back",
                22.sp,
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            textBetweenTwoLines(text = "Login")

            var emailText by remember { mutableStateOf("") }
            var passwordText by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = emailText,
                    singleLine = true,
                    onValueChange = { emailText = it },
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
                            text = "Email",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))

                )

                TextField(
                    value = passwordText,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
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
                        .padding(20.dp, 10.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)
                    .clickable {
                        navController.navigate("ForgetPassword")
                    },
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forget Password ?",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.arrowicon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(18.dp)
                        .rotate(180f)
                )

            }

            Spacer(modifier = Modifier.padding(10.dp))

            FullWidthButton(label = "Login", MaterialTheme.colorScheme.primary) {

                if (emailText.isEmpty()) {
                    snackBarMsg.value = "Email can't be empty"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }

                if (passwordText.isEmpty()) {
                    snackBarMsg.value = "Password can't be empty"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }

                val loginScope = scope.launch(Dispatchers.Main) {
                    loginViewModel.loginUser(emailText, passwordText).collect { result ->
                        when (result) {
                            is ResponseType.Success -> {

                                loginViewModel.getUserDetails().collect {
                                    when (it) {
                                        is ResponseType.Error -> {

                                        }

                                        is ResponseType.Loading -> {

                                        }

                                        is ResponseType.Success -> {
                                            showProgressBar.value = false

                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.popBackStack()

                                            if (it.data!!.city.equals("")) {
                                                navController.navigate("SelectCityScreen")
                                            } else {

                                                sharedPreferences.edit().putString("userName",it.data.userName).apply()
                                                sharedPreferences.edit().putString("userEmail",it.data.email).apply()
                                                sharedPreferences.edit().putString("userCity", it.data.city).apply()
                                                sharedPreferences.edit().putBoolean("isFilled",true).apply()

                                                navController.navigate("AllowLocation")
                                            }
                                        }
                                    }
                                }

                            }

                            is ResponseType.Error -> {
                                showProgressBar.value = false
                                snackBarMsg.value = result.errorMsg.toString()
                                openMySnackbar.value = true
                                Log.d("UpdateCurrentState", "success")
                            }

                            is ResponseType.Loading -> {
                                showProgressBar.value = true
                                Log.d("UpdateCurrentState", "loading")
                            }
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.padding(40.dp))
        }

        customProgressBar(show = showProgressBar.value, title = "Wait a moment...")


        SnackbarWithoutScaffold(
            snackBarMsg.value, openMySnackbar.value, { openMySnackbar.value = it }, Modifier.align(
                Alignment.BottomCenter
            )
        )
    }

}


@Composable
fun SnackbarWithoutScaffold(
    message: String,
    showSb: Boolean,
    openSnackbar: (Boolean) -> Unit,
    modifier: Modifier
) {

    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    SnackbarHost(
        modifier = modifier,
        hostState = snackState
    ) {
        Snackbar(
            modifier = Modifier.padding(8.dp),
            containerColor = MaterialTheme.colorScheme.onBackground
        ) {
            Row() {
                Icon(
                    Icons.Filled.Warning,
                    "",
                    Modifier.padding(end = 8.dp),
                    tint = MaterialTheme.colorScheme.background
                )

                Text(
                    text = message,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    modifier = Modifier.padding(0.dp, 1.dp, 0.dp, 0.dp)
                )
            }
        }
    }

    if (showSb) {
        LaunchedEffect(Unit) {
            snackScope.launch { snackState.showSnackbar(message) }
            openSnackbar(false)
        }
    }
}