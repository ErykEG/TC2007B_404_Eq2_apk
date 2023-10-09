package com.example.tc2007b_404_eq2_apk.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.screens.about.AboutPage
import com.example.tc2007b_404_eq2_apk.screens.busqueda.BusquedaPage
import com.example.tc2007b_404_eq2_apk.screens.detailsosc.DetailsOSC
import com.example.tc2007b_404_eq2_apk.screens.donativos.DonativosPage
import com.example.tc2007b_404_eq2_apk.screens.favoritos.FavoritosPage
import com.example.tc2007b_404_eq2_apk.screens.home.HomePage
import com.example.tc2007b_404_eq2_apk.screens.login.LoginPage
import com.example.tc2007b_404_eq2_apk.screens.logreg.LogRegPage
import com.example.tc2007b_404_eq2_apk.screens.logreg.LogRegPageOSC
import com.example.tc2007b_404_eq2_apk.screens.organizations.LoginOrg
import com.example.tc2007b_404_eq2_apk.screens.organizations.RegisterOrgPage
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
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var loggedIn by remember { mutableStateOf(appViewModel.isUserLoggedIn()) }
    var isHomePage by remember { mutableStateOf(false) }

    fun toggleDialog() {
        showDialog = !showDialog
    }

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
        /*NavigationItem(
            title = "Configuración",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            route = "SettingsPage"
        ),*/
        /*NavigationItem(
            title = "Registrar Nueva Cuenta",
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            route = "RegisterPage"
        ),*/
        /*NavigationItem(
            title = "Iniciar Sesión",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            route = "LoginPage"
        ),*/
        NavigationItem(
            title = "Iniciar Sesión/Registrar Usuario",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            route = "LogRegPage"
        ),
        NavigationItem(
            title = "Iniciar Sesión/Registrar OSC",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = "LogRegPageOSC"
        )
        /*NavigationItem(
            title = "Iniciar Sesión Org",
            selectedIcon = Icons.Filled.AccountBox,
            unselectedIcon = Icons.Outlined.AccountBox,
            route = "OrgLogin"
        )*/
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
            )
            /*NavigationItem(
                title = "Configuración",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                route = "SettingsPage"
            )*/
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
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
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
                            text = "¡Bienvenido! Sesión activa",
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
                            text = "Invitado",
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 18.sp
                            )
                        )
                        Text(
                            text = "Inicie sesión o regístrese para disfrutar al máximo de ConectAyuda.",
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        if (isHomePage) {
                            Text(text = "")
                        } else {
                            Text("")
                        }
                    },
                    actions = {
                        if (isHomePage) {
                            if (loggedIn) {
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(50.dp)
                                )
                                IconButton(
                                    onClick = {
                                        navController.navigate("HomePage")
                                    },
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                ) {
                                    Text(text = "Inicio")
                                }
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                                )
                                IconButton(
                                    onClick = {
                                        navController.navigate("FavoritosPage")
                                    },
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                ) {
                                    Text(text = "Favoritos")
                                }
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(50.dp)
                                )
                            }
                        }
                    },
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
                    }
                )
            }
        )
        {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = "HomePage") {
                    composable("HomePage") {
                        isHomePage = true
                        HomePage(navController)
                    }
                    composable("AboutPage") {
                        isHomePage = false
                        AboutPage(/*appViewModel*/)
                    }
                    composable("RegisterPage") {
                        isHomePage = false
                        RegisterPage(appViewModel, navController)
                    }
                    composable("RegisterOrg") {
                        isHomePage = false
                        RegisterOrgPage(appViewModel, navController)
                    }
                    composable("OrgLogin") {
                        isHomePage = false
                        LoginOrg(appViewModel, navController) { value ->
                            loggedIn = value
                        }
                    }
                    composable("LoginPage") {
                        isHomePage = false
                        LoginPage(appViewModel, navController) { value ->
                            loggedIn = value
                        }
                    }
                    composable("SettingsPage") {
                        isHomePage = false
                        SettingsPage(appViewModel, navController) { value ->
                            loggedIn = value
                        }
                    }
                    composable("DetailsOSC/{osc}") { backStackEntry ->
                        isHomePage = false
                        val osc = backStackEntry.arguments?.getString("osc")
                        if (osc != null) {
                            DetailsOSC(navController, osc, appViewModel)
                        }
                    }
                    composable("FavoritosPage") {
                        FavoritosPage(appViewModel)
                    }
                    composable("LogRegPage") {
                        isHomePage = false
                        LogRegPage(appViewModel, navController) { value ->
                            loggedIn = value
                        }
                    }
                    composable("LogRegPageOSC") {
                        isHomePage = false
                        LogRegPageOSC(appViewModel, navController) { value ->
                            loggedIn = value
                        }
                    }
                    composable("BusquedaPage"){
                        isHomePage = false
                        BusquedaPage(navController)
                    }
                    composable("DonativosPage") {
                        isHomePage = false
                        DonativosPage(navController)
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Cierre de Sesión", textAlign = TextAlign.Center) },
            text = {
                Text(
                    text = "¿Seguro que desea cerrar la sesión?",
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate("LoginPage")
                        loggedIn = false
                        appViewModel.deleteToken()
                        appViewModel.setLoggedOut()
                        showDialog = false
                        scope.launch {
                            drawerState.close()
                        }
                    }
                ) {
                    Text(text = "Cerrar Sesión")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}

