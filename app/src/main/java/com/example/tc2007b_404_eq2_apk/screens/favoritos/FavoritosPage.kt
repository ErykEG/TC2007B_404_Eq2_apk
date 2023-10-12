package com.example.tc2007b_404_eq2_apk.screens.favoritos

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tc2007b_404_eq2_apk.R
import com.example.tc2007b_404_eq2_apk.model.OrgResp
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.navigation.Screens
import com.example.tc2007b_404_eq2_apk.screens.home.OSCRow
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("MutableCollectionMutableState")
@Composable
fun FavoritosPage(navController: NavController, appViewModel: AppViewModel) {
    val favorites = appViewModel.favorites.filter { it.value }
    val userViewModel = UserViewModel(UserService.instance)
    var visibility by remember { mutableStateOf(true) }
    var favList by remember {
        mutableStateOf(OrgRespList())
    }

    userViewModel.getFav(appViewModel.getToken())

    LaunchedEffect(key1 = userViewModel) {
        userViewModel.favResult.collect { result ->
            if (result != null) {
                favList = result
            }
        }
    }

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = favList) {
                visibility = false
                OSCRow(osc = it) { osc ->
                    navController.navigate(route = Screens.DetailsOSC.name + "/$osc")
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

@Composable
fun OSCRow(osc: OrgResp, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick(osc._id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape
            ) {

                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }
            Text(text = osc.name)
        }
    }
}

