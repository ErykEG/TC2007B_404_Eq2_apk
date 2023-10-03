package com.example.tc2007b_404_eq2_apk.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.tc2007b_404_eq2_apk.model.OrgResp
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.navigation.Screens
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.service.UserService
import com.example.tc2007b_404_eq2_apk.util.constants.Constants
import com.example.tc2007b_404_eq2_apk.viewModel.OrgViewModel
import com.example.tc2007b_404_eq2_apk.viewModel.UserViewModel


@Composable
fun HomePage(navController: NavController) {
    val orgViewModel = OrgViewModel(OrgService.instance)
    var orgList by remember {
        mutableStateOf(OrgRespList())
    }
    orgViewModel.getOrgL()
    LaunchedEffect(key1 = orgViewModel) {
        orgViewModel.orgListResult.collect { result ->
            if (result != null) {
                orgList=result
            }
        }
    }

    /*val oscList: List<String> = listOf(
        "Arena",
        "Mayama AC",
        "Fundación Tuk"
    )*/

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = orgList) {
                OSCRow(osc = it) { osc ->
                    navController.navigate(route = Screens.DetailsOSC.name + "/$osc")
                }
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
