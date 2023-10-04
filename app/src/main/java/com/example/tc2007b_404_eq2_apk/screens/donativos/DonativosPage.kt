package com.example.tc2007b_404_eq2_apk.screens.donativos


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope

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
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DonativosPage(/*appviewModel: AppViewModel*/) {



    val corutineScope = rememberCoroutineScope()

    var cardNumber by remember { mutableStateOf("") }
    var cardExpiration by remember { mutableStateOf("") }
    var cardCVC by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text("Donate", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            TextField(value = cardNumber, onValueChange = {
                cardNumber = it
            }, placeholder = {
                Text("Card Number")
            })

            TextField(
                value = cardExpiration,
                onValueChange = {
                    cardExpiration = it
                },
                placeholder = {
                    Text("Expiration")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            TextField(value = cardCVC, onValueChange = {
                cardCVC = it
            }, placeholder = {
                Text("cardCVC")
            })

            Button(onClick = {
                corutineScope.launch {
                    delay(2.seconds)
                    snackbarHostState.showSnackbar("Pago Realizado ")
                }


            }) {
                Text(text = "Pagar")
            }


            // Text("${loginResult.token}  ")
            //Text(" ${loginResult.message}")


        }
    }

}