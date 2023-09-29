package com.example.tc2007b_404_eq2_apk.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.screens.about.AboutPage
import com.example.tc2007b_404_eq2_apk.screens.detailsosc.DetailsOSC
import com.example.tc2007b_404_eq2_apk.screens.home.HomePage
import com.example.tc2007b_404_eq2_apk.screens.login.LoginPage
import com.example.tc2007b_404_eq2_apk.screens.organizations.LoginOrg
import com.example.tc2007b_404_eq2_apk.screens.organizations.RegisterOrgPage
import com.example.tc2007b_404_eq2_apk.screens.protect.TestProtectedPage
import com.example.tc2007b_404_eq2_apk.screens.register.RegisterPage
import com.example.tc2007b_404_eq2_apk.screens.settings.SettingsPage

import kotlinx.coroutines.launch


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(appViewModel: AppViewModel, navController: NavHostController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var showDialog by remember { mutableStateOf(false) }

    fun toggleDialog() {
        showDialog = !showDialog
    }


    var loggedIn by remember {
        mutableStateOf(appViewModel.isUserLoggedIn())
    }


    /* LaunchedEffect(appViewModel.isUserLoggedIn()) {
         appViewModel.isInitialized.collect { result ->

             loggedIn = result

         }
     }*/


    /* LaunchedEffect(appViewModel.isUserLoggedIn()) {
         loggedIn = appViewModel.isUserLoggedIn()
     }*/

    val items = if (!loggedIn) mutableListOf(
        NavigationItem(
            title = "Inicio",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = "HomePage"
        ),
        NavigationItem(
            title = "Términos y Condiciones",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = "AboutPage"
        ),
        NavigationItem(
            title = "Configuración",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            route = "SettingsPage"
        ),
        NavigationItem(
            title = "Registrar Nueva Cuenta",
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            route = "RegisterPage"
        ),
        NavigationItem(
            title = "Iniciar Sesión",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            route = "LoginPage"
        ),
        NavigationItem(
            title = "Iniciar Sesión Org",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = "OrgLogin"
        ),
        NavigationItem(
            title = "Test Protected Page",
            selectedIcon = Icons.Filled.Lock,
            unselectedIcon = Icons.Outlined.Lock,
            route = "TestProtectedPage"
        ),
    ) else
        mutableListOf(
            NavigationItem(
                title = "Inicio",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = "HomePage"
            ),
            NavigationItem(
                title = "Términos y Condiciones",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
                route = "AboutPage"
            ),
            NavigationItem(
                title = "Configuración",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                route = "SettingsPage"
            ),

            NavigationItem(
                title = "Test Protected Page",
                selectedIcon = Icons.Filled.Lock,
                unselectedIcon = Icons.Outlined.Lock,
                route = "TestProtectedPage"
            )
        )


    if (appViewModel.isAdmin()) {
        items.add(
            NavigationItem(
                title = "Add Organization",
                selectedIcon = Icons.Filled.List,
                unselectedIcon = Icons.Outlined.List,
                route = "RegisterOrg"
            )
        )
    }


    ModalNavigationDrawer(drawerContent = {

        ModalDrawerSheet {

            Spacer(modifier = Modifier.height(16.dp))

            items.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = {
                        Text(text = item.title)
                    },
                    selected = index == selectedItemIndex,
                    onClick = {
                        navController.navigate(item.route)
                        selectedItemIndex = index
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }

            if (loggedIn) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            text = "Bienvenido! Sesión activa",
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 18.sp
                            )
                        )
                        Button(onClick = {


                            toggleDialog()

                        }) {
                            Text(text = "Finalizar Sesión")
                        }
                    }

                }

            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            text = "Usuario invitado",
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 18.sp
                            )
                        )
                        Text(
                            text = "Registra una cuenta o inicia sesión para aprovechar al máximo la aplicación. ",
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp
                            )
                        )

                    }

                }
            }


        }
    }, drawerState = drawerState) {


        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "ConectAyuda",
                modifier = Modifier.fillMaxWidth()
                    .padding(60.dp)) },
                navigationIcon = {

                IconButton(onClick = {

                    if (drawerState.isClosed) {
                        scope.launch {
                            drawerState.open()
                        }
                    } else {
                        scope.launch {
                            drawerState.close()
                        }
                    }


                }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Drawer Menu.")
                }
            })
        }) {

            Box(modifier = Modifier.padding(it)) {

                NavHost(navController = navController, startDestination = "HomePage") {

                    composable("HomePage") {
                        HomePage(navController)
                    }

                    composable("AboutPage") {
                        AboutPage(appViewModel)
                    }

                    composable("RegisterPage") {
                        RegisterPage(appViewModel, navController)
                    }

                    composable("RegisterOrg") {
                        RegisterOrgPage(appViewModel)
                    }

                    composable("OrgLogin") {
                        LoginOrg(appViewModel)
                    }


                    composable("LoginPage") {
                        LoginPage(appViewModel, navController) { value ->
                            loggedIn = value
                        }
                    }


                    composable("TestProtectedPage") {
                        TestProtectedPage(appViewModel)
                    }

                    composable("SettingsPage") {
                        SettingsPage(appViewModel, navController) { value ->
                            // Update the loggedIn state in MainPage when it changes
                            loggedIn = value
                            //  selectedItemIndex = if (value) 1 else 0
                        }
                    }
                    composable("DetailsOSC/{osc}") { backStackEntry ->
                        val osc = backStackEntry.arguments?.getString("osc")
                        DetailsOSC(navController, osc)
                    }
                }
            }
        }
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { toggleDialog() },
            title = { Text(text = "Confirm Logout") },
            text = { Text(text = "Are you sure you want to log out?") },
            confirmButton = {
                Button(
                    onClick = {
                        // Perform logout action here
                        // You can use navController to navigate to the login page
                        navController.navigate("LoginPage")
                        loggedIn = false // Update your loggedIn state
                        appViewModel.deleteToken()
                        appViewModel.setLoggedOut()
                        toggleDialog() // Close the dialog
                        scope.launch {
                            drawerState.close()
                        }
                    }
                ) {
                    Text(text = "Logout")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        toggleDialog() // Close the dialog
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }

}
