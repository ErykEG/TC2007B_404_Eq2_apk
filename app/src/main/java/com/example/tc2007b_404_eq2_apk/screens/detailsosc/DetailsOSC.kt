package com.example.tc2007b_404_eq2_apk.screens.detailsosc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tc2007b_404_eq2_apk.R
import com.example.tc2007b_404_eq2_apk.model.PageList
import com.example.tc2007b_404_eq2_apk.model.Star
import com.example.tc2007b_404_eq2_apk.model.Stringid
import com.example.tc2007b_404_eq2_apk.service.PagService
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.PagViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("MutableCollectionMutableState")
@Composable
fun DetailsOSC(navController: NavController, id: String, appViewModel: AppViewModel) {

    val pageViewModel = PagViewModel(PagService.instance)

    val userViewModel = UserViewModel(UserService.instance)


    val intentLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){}

    var visibility by remember { mutableStateOf(true) }

    var pagina by remember {
        mutableStateOf(PageList())
    }
        
    var mensj by remember {
        mutableStateOf(Star(message = false))
    }

    val mensaje by remember { mutableStateOf("¡Descarga ConectAyuda y unete a la red más grande " +
            "de Organizaciónes de la sociedad civil de todo México!") }

    val intent = remember { Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, mensaje)
        type = "text/plain"
    } }

    val shareLauncher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        // Handle the result if needed
    }

    val s by remember {
        mutableStateOf(Stringid(id))
    }
    pageViewModel.getP(s)
    LaunchedEffect(key1 = pageViewModel) {
        pageViewModel.pageResult.collect { result ->
            if (result != null) {
                pagina = result
            }
        }
    }

    userViewModel.ifStarred(appViewModel.getToken(), id)
    LaunchedEffect(key1 = userViewModel) {
        userViewModel.isF.collect { result ->
            if (result != null) {
                mensj = result
            }
        }
    }
    var bool by remember {
        mutableStateOf(mensj.message)
    }
    bool=mensj.message
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.weight(0.03f))
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = if (bool) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Favorite",
                tint = if (bool) Yellow else Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if(!bool){
                            userViewModel.addUserFavoriteOrganization(appViewModel.getToken(), id)
                            Log.d("hola", appViewModel.getToken())
                            Log.d("hola", id)


                        }
                        else{userViewModel.removeUserFavoriteOrganization(appViewModel.getToken(), id)
                            }
                        bool=!bool
                        Log.d("hola", bool.toString())
                    }
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        shareLauncher.launch(Intent.createChooser(intent, "Compartir a:"))
                    }
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = id,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Transparent
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                items(items = pagina) { item ->
                    visibility = false
                    Text(
                        text = item.titulo,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(1.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(items = pagina) { item ->
                    visibility = false
                    Text(
                        text = "¿Quiénes son?",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = item.desc,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    AsyncImage(model = item.img, contentDescription = "loquesea")

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .size(100.dp)
                                .background(
                                    Color(0xFF6633FF).copy(alpha = 0.7f),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    intentLauncher.launch(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(item.linkb1)
                                        )
                                    )
                                }
                        ) {
                            Text(
                                text = "Sobre nosotros",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .size(100.dp)
                                .background(
                                    Color(0xFF33FF99).copy(alpha = 0.7f),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    intentLauncher.launch(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(item.linkb2)
                                        )
                                    )
                                }
                        ) {
                            Text(
                                text = "Objetivos",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .size(100.dp)
                                .background(
                                    Color(0xFFFF3333).copy(alpha = 0.7f),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    navController.navigate("DonativosPage")
                                }
                        ) {
                            Text(
                                text = "Dona aquí",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .size(100.dp)
                                .background(
                                    Color(0xFF9933CC).copy(alpha = 0.7f),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    intentLauncher.launch(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(item.linkb4)
                                        )
                                    )
                                }
                        ) {
                            Text(
                                text = "Ubicación",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }

        }
    }
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    imageModel = R.drawable.logoloading,
                    modifier = Modifier.size(200.dp),
                    contentDescription = null
                )
            }
        }
    }
}
