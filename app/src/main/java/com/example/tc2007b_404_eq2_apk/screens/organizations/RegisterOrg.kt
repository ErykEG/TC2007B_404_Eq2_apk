package com.example.tc2007b_404_eq2_apk.screens.organizations

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.NavController
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import com.example.tc2007b_404_eq2_apk.model.OrgRegister
import com.example.tc2007b_404_eq2_apk.model.OrgRegisterResponse
import com.example.tc2007b_404_eq2_apk.screens.register.showToast
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.OrgViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterOrgPage(
    appViewModel: AppViewModel = AppViewModel(LocalContext.current),
    navController: NavController
) {

    val orgViewModel = OrgViewModel(OrgService.instance)

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var validarpassword by remember {
        mutableStateOf("")
    }
    var token by remember {
        mutableStateOf("")
    }

    var orgRegisterResult by remember {
        mutableStateOf(OrgRegisterResponse())
    }

    var termsAccepted by remember {
        mutableStateOf(false)
    }

    var isFieldsFilled by remember {
        mutableStateOf(false)
    }

    var showPrivacyNotice by remember { mutableStateOf(false) }
    var showTags by remember { mutableStateOf(false) }

    val nombres = listOf(
        "Autismo", "Cancer de mama", "Vejez", "Educación", "Cultura",
        "Refugio", "LGBTQ", "Psicologia", "Terapia", "Salud"
    )
    val selectedIndices = remember { mutableStateListOf<Int>() }

    LaunchedEffect(name, email, description, password, validarpassword, token) {
        isFieldsFilled = name.isNotBlank() &&
                email.isNotBlank() &&
                description.isNotBlank() &&
                password.isNotBlank() &&
                validarpassword.isNotBlank() &&
                token.isNotBlank()
    }


    LaunchedEffect(orgViewModel) {
        orgViewModel.orgRegisterResult.collect { result ->
            if (result != null) {
                orgRegisterResult = result
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Agregar Nueva Organizacion", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        TextField(value = name, onValueChange = {
            name = it
        }, placeholder = {
            Text("Nombre")
        })

        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        TextField(value = description, onValueChange = {
            description = it
        }, placeholder = {
            Text("Descripcion")
        },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
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

        TextField(value = token, onValueChange = {
            token = it
        }, placeholder = {
            Text("Token de acceso")
        }, visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                showTags = true
            }
        ) {
            Text(
                text = "Seleccione sus tags",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
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

                val organization =
                    OrgRegister(name, description, email, password, validarpassword, token)

                orgViewModel.addOrganization(appViewModel.getToken(), organization)
            },
            enabled = termsAccepted && isFieldsFilled
        ) {
            Text(text = "Registrar Nueva Organización")
        }

        if (orgRegisterResult.message != null) {
            showToast(message = "Organizacion registrada exitosamente")
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

    }

    AnimatedVisibility(
        visible = showTags,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Encabezado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                showTags = false
                            }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text("Selecciona algún tag para agregar")
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    items(nombres) { nombre ->
                        val index = nombres.indexOf(nombre)
                        val isSelected = selectedIndices.contains(index)

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .size(50.dp)
                                .clickable {
                                    if (isSelected) {
                                        selectedIndices.clear()
                                    } else {
                                        selectedIndices.clear()
                                        selectedIndices.add(index)
                                    }
                                }
                                .background(
                                    color = if (isSelected) Color.Cyan else Color.DarkGray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                        ) {
                            Text(
                                text = nombre,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { showTags = false }
                    ) {
                        Text("Seleccionar")
                    }
                }
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


@SuppressLint("ComposableNaming")
@Composable
fun showToast(message: String) {
    val context = LocalContext.current
    val duration = Toast.LENGTH_SHORT
    val toast = Toast.makeText(context, message, duration)
    toast.show()
}