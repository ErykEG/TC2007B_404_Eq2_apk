package com.example.tc2007b_404_eq2_apk.screens.favoritos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.tc2007b_404_eq2_apk.model.OrgResp
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel

@Composable
fun FavoritosPage(appViewModel: AppViewModel) {

    val favorites = appViewModel.favorites.filter { it.value }

}
