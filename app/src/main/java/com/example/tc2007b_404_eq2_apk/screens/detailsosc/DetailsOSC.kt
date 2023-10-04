package com.example.tc2007b_404_eq2_apk.screens.detailsosc

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tc2007b_404_eq2_apk.model.PageList
import com.example.tc2007b_404_eq2_apk.model.Stringid
import com.example.tc2007b_404_eq2_apk.service.PagService
import com.example.tc2007b_404_eq2_apk.viewModel.PagViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun DetailsOSC(navController: NavController, id: String) {

    val pageViewModel = PagViewModel(PagService.instance)

    var pagina by remember {
        mutableStateOf(PageList())
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
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
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
                .padding(16.dp)
        ) {
            Text(
                text = id,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Transparent
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(items = pagina) { item ->
                    // Título
                    Text(
                        text = item.titulo,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = item.desc,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(4.dp)
                    )
                    Text(
                        text = item.img,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón 1
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .size(100.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                        .clickable {}
                ) {
                    Text(
                        text = "Botón 1",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .size(100.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                        .clickable {}
                ) {
                    Text(
                        text = "Botón 2",
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
                // Botón 3
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .size(100.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                        .clickable {}
                ) {
                    Text(
                        text = "Botón 3",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                // Botón 4
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .size(100.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                        .clickable {}
                ) {
                    Text(
                        text = "Botón 4",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
