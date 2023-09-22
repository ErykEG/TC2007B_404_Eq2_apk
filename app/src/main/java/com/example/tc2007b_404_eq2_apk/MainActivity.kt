package com.example.tc2007b_404_eq2_apk

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tc2007b_404_eq2_apk.navigation.MainPage
import com.example.tc2007b_404_eq2_apk.ui.theme.TC2007B_404_Eq2_apkTheme
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModelFactory

import com.example.tc2007b_404_eq2_apk.ui.theme.TC2007B_404_Eq2_apkTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val appViewModel: AppViewModel = viewModel(factory = AppViewModelFactory(context))

            var configLoaded by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(appViewModel.isUserLoggedIn()) {
                delay(500)
                appViewModel.isInitialized.collect { result ->
                    configLoaded = result
                }
            }

            TC2007B_404_Eq2_apkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (configLoaded) {
                        MainPage(appViewModel)
                    } else {
                        // Show a loading indicator or splash screen
                        /*CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp),
                            color = Color.Blue,
                            strokeWidth = 8.dp
                        )*/
                        /*Image(
                            painter = painterResource(R.drawable.logoloading),
                            contentDescription = null, // You can provide a description if needed
                            modifier = Modifier.fillMaxSize() // Adjust as needed
                        )
                        */
                        Text(text = "Loading...")


                    }
                }
            }
        }
    }
}