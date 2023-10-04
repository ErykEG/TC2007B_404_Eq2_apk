package com.example.tc2007b_404_eq2_apk.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.model.UserLoginResponse
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginPage(
    appviewModel: AppViewModel,
    navController: NavHostController,
    onLoggedInChanged: (Boolean) -> Unit
) {


    val userviewModel = UserViewModel(UserService.instance)

    var telefono by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var loginResult by remember {
        mutableStateOf(UserLoginResponse())
    }

    /*var termsAccepted by remember {
        mutableStateOf(false)
    }*/

    var isFieldsFilled by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(telefono, password) {
        isFieldsFilled = telefono.isNotBlank() && password.isNotBlank()
    }

    LaunchedEffect(key1 = userviewModel) {
        userviewModel.loginResult.collect { result ->
            if (result != null) {
                loginResult = result
                loginResult.token?.let {
                    appviewModel.storeValueInDataStore(it, Constants.TOKEN)
                    appviewModel.setToken(it)
                    appviewModel.setLoggedIn()
                    navController.navigate("HomePage")

                    Log.d("DATASTORE", "Token saved: ${it}")
                }
                loginResult.isAdmin.let {
                    appviewModel.storeValueInDataStore(it, Constants.ISADMIN)
                    appviewModel.setIsAdmin(it)
                }

                onLoggedInChanged(true)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Iniciar Sesión", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        TextField(value = telefono, onValueChange = {
            telefono = it
        }, placeholder = {
            Text("Teléfono")
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

        /*Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                termsAccepted = !termsAccepted
            }
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = {
                    termsAccepted = it
                }
            )
            Text(
                text = "Aceptar términos y condiciones",
                modifier = Modifier.padding(start = 8.dp)
            )
        }*/
        Button(
            onClick = {
                if (isFieldsFilled) {
                    userviewModel.loginUser(telefono.trim().toInt(), password)
                }
            },
            enabled = /*TermsAccepted &&*/ isFieldsFilled
        ) {
            Text(text = "Ingresar")
        }
    }
}

// Text("${loginResult.token}  ")
//Text(" ${loginResult.message}")