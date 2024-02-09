package com.tellingus.tellingme.presentation.ui.feature.myspace

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations

@Composable
fun MySpaceScreen(
    navController: NavController,
) {

    Column {
        Text(text = "나의 공간")

        Button(onClick = {
            navController.navigate(HomeDestinations.RECORD)
        }) {
            Text(text = "기록하기")
        }
    }
}



@Preview
@Composable
fun MySpaceScreenPreview() {
//    MySpaceScreen {}
}
