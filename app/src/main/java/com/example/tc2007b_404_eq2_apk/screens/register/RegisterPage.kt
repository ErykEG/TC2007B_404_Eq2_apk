package com.example.tc2007b_404_eq2_apk.screens.register

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tc2007b_404_eq2_apk.screens.organizations.showToast
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import androidx.compose.material3.MaterialTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterPage(
    appViewModel: AppViewModel = AppViewModel(LocalContext.current),
    navController: NavHostController,
) {

    val viewModel = UserViewModel(UserService.instance)
    var showDelayedText by remember { mutableStateOf(false) }

    var showPrivacyNotice by remember { mutableStateOf(false) }

    var telefono by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var validarpassword by remember {
        mutableStateOf("")
    }

    var registrationResult by remember { mutableStateOf<UserViewModel.ApiResult?>(null) }

    var termsAccepted by remember {
        mutableStateOf(false)
    }

    var isFieldsFilled by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(telefono, password, validarpassword) {
        isFieldsFilled =
            telefono.isNotBlank() && password.isNotBlank() && validarpassword.isNotBlank()
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.registrationResult.collect { result ->
            registrationResult = result
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Registrar Usuario", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        TextField(value = telefono, onValueChange = {
            telefono = it
        }, placeholder = {
            Text("Teléfono de contacto")
        })

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text("Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        TextField(value = validarpassword, onValueChange = {
            validarpassword = it
        }, placeholder = {
            Text("Confirma tu contraseña")
        }, visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                if (termsAccepted) {
                    termsAccepted = false
                } else {
                    showPrivacyNotice = true
                }
            }
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = {
                    if (termsAccepted) {
                        termsAccepted = false
                    } else {
                        showPrivacyNotice = true
                    }
                }
            )
            Text(
                text = "Aceptar términos y condiciones",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = {
                if (termsAccepted && isFieldsFilled) {
                    viewModel.addUser(telefono.trim().toInt(), password)
                }
            },
            enabled = termsAccepted && isFieldsFilled
        ) {
            Text(text = "Registrar Usuario")
        }
        /*Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("AboutPage")
                }
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Términos y Condiciones",
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        }*/

        LaunchedEffect(showDelayedText) {
            if (showDelayedText) {
                launch {

                    delay(5000)
                    showDelayedText = false
                    navController.navigate("LoginPage")
                }
            }
        }


        if (showDelayedText) {

            Text(text = "Registro Exitoso")
            Text(text = "En 5 segundos serás redirigido a la pantalla para iniciar sesión.")
        }

        when (val result = registrationResult) {
            is UserViewModel.ApiResult.Success -> {
                Log.d("REGISTER", result.message)
                showToast("${result.message}")
                showDelayedText = true
            }

            is UserViewModel.ApiResult.Error -> {
                showToast("${result.errorMessage}")
                Log.d("REGISTER", result.errorMessage)
            }

            else -> {
                Log.d("REGISTER", result.toString())
            }
        }

    }

    AnimatedVisibility(
        visible = showPrivacyNotice,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Aviso de Privacidad")
                    }
                )
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Text(
                                text = Constants.avisoDePrivacidad,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }


                        item {
                            Text(
                                text = "Acepto a los términos y condiciones.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        item {
                            Row {
                                Button(
                                    onClick = {
                                        showPrivacyNotice = false
                                        termsAccepted = false
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "Denegar")
                                }
                            }

                        }
                        item {
                            Row {
                                Button(
                                    onClick = {
                                        showPrivacyNotice = false
                                        termsAccepted = true
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "Aceptar")
                                }
                            }

                        }
                    }
                }
            })
    }
}


@Composable
fun showToast(message: String) {
    val context = LocalContext.current
    val duration = Toast.LENGTH_SHORT
    val toast = Toast.makeText(context, message, duration)
    toast.show()
}