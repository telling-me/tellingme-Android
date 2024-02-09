package com.tellingus.tellingme.presentation.ui.feature.otherspace.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tellingus.tellingme.presentation.ui.common.navigation.OtherSpaceDestinations

@Composable
fun OtherSpaceDetailScreen(
    navController: NavController,
) {
    Column {

        Text(text = "모두의 공간 상세")

        Button(onClick = {
            navController.navigate(OtherSpaceDestinations.OTHER_SPACE)
        }) {
            Text("모두의 공간으로 이동")
        }
    }


}