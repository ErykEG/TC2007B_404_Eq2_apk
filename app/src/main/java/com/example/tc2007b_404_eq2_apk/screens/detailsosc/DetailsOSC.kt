package com.example.tc2007b_404_eq2_apk.screens.detailsosc

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.model.Page
import com.example.tc2007b_404_eq2_apk.model.PageList
import com.example.tc2007b_404_eq2_apk.model.Stringid
import com.example.tc2007b_404_eq2_apk.navigation.Screens
import com.example.tc2007b_404_eq2_apk.screens.home.OSCRow
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.service.PagService
import com.example.tc2007b_404_eq2_apk.viewModel.OrgViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.PagViewModel

@Composable
fun DetailsOSC(navController: NavController, id: String) {

    val pageViewModel = PagViewModel(PagService.instance)

    var pagina by remember {
        mutableStateOf(PageList())
    }

    var s by remember {
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
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Row(horizontalArrangement = Arrangement.Start){
            Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.popBackStack()
                }
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$id", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(items = pagina) {
                    Text(it.titulo)
                    Text(it.desc)
                    Text(it.img)
                }
                }

        }
    }
}
