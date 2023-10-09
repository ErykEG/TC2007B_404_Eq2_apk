package com.example.tc2007b_404_eq2_apk.screens.favoritos
import androidx.compose.runtime.Composable
import com.example.tc2007b_404_eq2_apk.viewModel.AppViewModel

@Composable
fun FavoritosPage(appViewModel: AppViewModel) {

    val favorites = appViewModel.favorites.filter { it.value }

}
