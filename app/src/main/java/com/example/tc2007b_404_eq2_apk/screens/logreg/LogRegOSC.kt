package com.example.tc2007b_404_eq2_apk.screens.logreg

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tc2007b_404_eq2_apk.R
import com.example.tc2007b_404_eq2_apk.model.UserLoginResponse
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel

@Composable
fun LogRegPageOSC(
    appviewModel: AppViewModel,
    navController: NavHostController,
    onLoggedInChanged: (Boolean) -> Unit
){
    val userviewModel = UserViewModel(UserService.instance)
    var loginResult by remember {
        mutableStateOf(UserLoginResponse())
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
    Column (modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(
            text = "ConectAyuda",
            style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 100.dp),
        )
        Image(
            painter = painterResource(R.drawable.manos2),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    navController.navigate("OrgLogin")
                })
            {
                Text("Iniciar Sesión")
            }
            Button(
                onClick = {
                    navController.navigate("RegisterOrg")
                })
            {
                Text("Registrar Organización")
            }
        }
    }
}

