package com.example.tc2007b_404_eq2_apk.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tc2007b_404_eq2_apk.R
import com.example.tc2007b_404_eq2_apk.model.OrgResp
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.navigation.Screens
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.viewModel.OrgViewModel
import com.skydoves.landscapist.glide.GlideImage


@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomePage(navController: NavController) {
    val orgViewModel = OrgViewModel(OrgService.instance)
    var orgList by remember {
        mutableStateOf(OrgRespList())
    }
    var visibility by remember { mutableStateOf(true) }

    val backgroundColor = if (isSystemInDarkTheme()) {
        Color.DarkGray
    } else {
        Color.LightGray
    }

    orgViewModel.getOrgL()

    LaunchedEffect(key1 = orgViewModel) {
        orgViewModel.orgListResult.collect { result ->
            if (result != null) {
                orgList = result
            }
        }
    }

    /*val oscList: List<String> = listOf(
        "Arena",
        "Mayama AC",
        "Fundación Tuk"
    )*/

    Column(modifier = Modifier.padding(12.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(color = backgroundColor)
                .clickable {
                    navController.navigate("BusquedaPage")
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Presiona para hacer búsqueda")
            }
        }

        LazyColumn {
            items(items = orgList) {
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
