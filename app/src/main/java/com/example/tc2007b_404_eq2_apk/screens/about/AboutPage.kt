package com.example.tc2007b_404_eq2_apk.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel

@Composable
fun AboutPage(appviewModel: AppViewModel) {

    Column {
        Text(text = "Welcome to AboutPage")
    }
}