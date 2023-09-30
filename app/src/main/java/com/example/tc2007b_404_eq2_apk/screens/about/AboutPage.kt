package com.example.tc2007b_404_eq2_apk.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel


@Composable
fun AboutPage(appviewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Términos y Condiciones de Uso",
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Por favor, lea detenidamente los siguientes términos y condiciones antes de utilizar nuestra aplicación móvil.",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "1. Aceptación de Términos",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Al utilizar nuestra aplicación móvil, usted acepta cumplir con estos términos y condiciones de uso. Si no está de acuerdo con estos términos, por favor, absténgase de utilizar la aplicación.",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Agregar más secciones de términos y condiciones según sea necesario

        Text(
            text = "Última actualización: [Fecha de la última actualización]",
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}



