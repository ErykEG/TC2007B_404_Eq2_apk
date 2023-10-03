package com.example.tc2007b_404_eq2_apk.screens.busqueda

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BusquedaPage(
    navController: NavHostController
) {
    val nombres = listOf(
        "Autismo", "Cancer de mama", "Vejez", "Educación", "Cultura",
        "Refugio", "LGBTQ", "Psicologia", "Terapia", "Salud"
    )
    val selectedIndices = remember { mutableStateListOf<Int>() }

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
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text("Selecciona algún tag para buscar")
        }

        // Lista de elementos
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Hace que la lista tome el espacio restante en la pantalla
                .padding(16.dp)
        ) {
            items(nombres) { nombre ->
                val index = nombres.indexOf(nombre)
                val isSelected = selectedIndices.contains(index)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            if (isSelected) {
                                selectedIndices.clear() // Deselecciona todos los elementos
                            } else {
                                selectedIndices.clear()
                                selectedIndices.add(index)
                            }
                        }
                        .background(
                            color = if (isSelected) Color.Cyan else Color.Gray
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = nombre)
                    }
                }
            }
        }

        // Botón de búsqueda
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    // Realiza acciones con el elemento seleccionado si es necesario
                }
            ) {
                Text("Buscar")
            }
        }
    }
}