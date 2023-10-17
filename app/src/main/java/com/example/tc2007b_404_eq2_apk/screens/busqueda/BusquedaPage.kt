package com.example.tc2007b_404_eq2_apk.screens.busqueda

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tc2007b_404_eq2_apk.model.ArrT
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.viewModel.OrgViewModel

@Composable
fun BusquedaPage(
    navController: NavHostController
) {
    val orgViewModel = OrgViewModel(OrgService.instance)

    val nombres = listOf(
        "Autismo", "Cancer de mama", "Vejez", "Educación", "Cultura",
        "Refugio", "LGBTQ", "Psicologia", "Terapia", "Salud"
    )
    val selectedIndices = remember { mutableStateListOf<String>() }

    var orgList2 by remember {
        mutableStateOf(OrgRespList())
    }

    LaunchedEffect(key1 = orgViewModel) {
        orgViewModel.filtResult.collect { result ->
            if (result != null) {
                orgList2 = result
            }
        }
    }

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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {
            items(nombres) { nombre ->
                val index = nombres.indexOf(nombre)
                val isSelected = selectedIndices.contains(nombre)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .size(50.dp)
                        .clickable {
                            if (isSelected) {
                                selectedIndices.remove(nombre)
                            } else {
                                selectedIndices.add(nombre)
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
                onClick = {
                    val r = ArrT(selectedIndices.toTypedArray())
                    orgViewModel.getFilt(r)
                    Log.d("t", "${r}")
                }
            ) {
                Text("Buscar")
            }
        }
    }
}

