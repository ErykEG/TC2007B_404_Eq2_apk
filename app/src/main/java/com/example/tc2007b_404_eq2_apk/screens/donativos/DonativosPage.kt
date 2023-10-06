package com.example.tc2007b_404_eq2_apk.screens.donativos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun DonativosPage(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
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
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Donativos", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(16.dp))

            TextField(
                value = cardNumber,
                onValueChange = {
                    if (it.length <= 16) {
                        cardNumber = it
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = {
                    Text("Número de Tarjeta")
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = cardExpiration,
                    onValueChange = { newInput ->
                        val cleanedInput = newInput.take(5).filter { it.isDigit() }
                        cardExpiration = when {
                            cleanedInput.length == 2 && newInput.length > 2 && newInput[2] != '/' -> cleanedInput + "/" + newInput.drop(2)
                            cleanedInput.length <= 2 -> cleanedInput
                            cleanedInput.length > 3 -> cleanedInput.take(2) + "/" + cleanedInput.drop(2).take(2)
                            else -> cleanedInput
                        }
                    },
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    placeholder = {
                        Text("Expiration")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                TextField(
                    value = cardCVC,
                    onValueChange = {
                        if (it.length <= 3) {
                            cardCVC = it
                        }
                    },
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    placeholder = {
                        Text("CVC")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }

            Button(
                onClick = {
                    if (cardNumber.length == 16 && cardExpiration.length == 5 && cardCVC.length == 3) {
                        coroutineScope.launch {
                            delay(2.seconds)
                            snackbarHostState.showSnackbar("Pago Realizado")
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                enabled = cardNumber.length == 16 && cardExpiration.length == 5 && cardCVC.length == 3
            ) {
                Text(text = "Pagar")
            }
        }
    }
}