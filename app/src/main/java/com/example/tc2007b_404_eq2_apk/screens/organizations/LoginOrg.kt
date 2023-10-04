package com.example.tc2007b_404_eq2_apk.screens.organizations

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
import com.example.tc2007b_404_eq2_apk.model.UserLoginResponse
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginOrg(
    appviewModel: AppViewModel,
    navController: NavHostController,
    onLoggedInChanged: (Boolean) -> Unit
) {


    val userviewModel = UserViewModel(UserService.instance)

    var email by remember {
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

    LaunchedEffect(email, password) {
        isFieldsFilled = email.isNotBlank() && password.isNotBlank()
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

        TextField(value = email, onValueChange = {
            email = it
        }, placeholder = {
            Text("Email")
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
                    userviewModel.loginUser(email.trim().toInt(), password)
                }
            },
            enabled = /*termsAccepted &&*/ isFieldsFilled
        ) {
            Text(text = "Ingresar")
        }
    }
}

/*val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val googleSignInClient = getGoogleLoginAuth(context)
    val userInfo = remember { mutableStateOf<GoogleSignInAccount?>(null) } // Store user info


    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)

                    val accountDetails = task.result
                    //Logging
                    Log.d("PRUEBA", "Email: ${accountDetails?.email}")


                    accountDetails.idToken?.let { Log.d("PRUEBA", accountDetails.idToken) }
                    accountDetails.id?.let { Log.d("PRUEBA", accountDetails.id) }

                    userInfo.value = accountDetails
                }
            }

        }


    Column {
        if (userInfo.value == null) {

            SignInButton(
                text = "Sign in with Google",
                loadingText = "Signing in...",
                isLoading = isLoading,
                icon = painterResource(id = R.drawable.ic_google_logo),
                onClick = {
                    isLoading = true
                    startForResult.launch(googleSignInClient?.signInIntent)
                }

            )

        } else {
            // Show user info when available
            UserInfo(userInfo.value!!, googleSignInClient)
        }
    }*/


/*private fun getGoogleLoginAuth(context: Context): GoogleSignInClient {


    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken("784626069081-r392adj9cdk77eevjg6otvqlb7omgvih.apps.googleusercontent.com")
        .requestId()
        .requestProfile()
        .build()
    return GoogleSignIn.getClient(context, gso)
}


@Composable
fun UserInfo(account: GoogleSignInAccount, googleSignInClient: GoogleSignInClient?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome, ${account.displayName}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(text = "Email: ${account.email}")
        // You can add more user information here as needed

        // Add a button to log out
        Button(
            onClick = {
                signOutFromGoogle(googleSignInClient)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = "Sign Out", modifier = Modifier.padding(6.dp))
        }

    }
}


// Function to sign out
private fun signOutFromGoogle(googleSignInClient: GoogleSignInClient?) {
    googleSignInClient?.signOut()
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign-out was successful, you can perform any additional actions here
                // For example, you can reset the user info and navigate to the login screen
            } else {
                // Handle sign-out failure
                // You may want to display an error message or take appropriate action
            }
        }
}

@Composable
fun SignInButton(
    text: String,
    loadingText: String = "Signing in...",
    icon: Painter,
    isLoading: Boolean = false,
    borderColor: Color = Color.LightGray,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable(
            enabled = !isLoading,
            onClick = onClick
        ),
        border = BorderStroke(width = 1.dp, color = borderColor)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = icon,
                contentDescription = "SignInButton",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(text = if (isLoading) loadingText else text)
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp
                )
            }
        }
    }*/