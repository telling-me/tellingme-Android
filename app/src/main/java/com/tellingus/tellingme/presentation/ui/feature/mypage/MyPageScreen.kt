package com.tellingus.tellingme.presentation.ui.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.component.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.common.navigation.MyPageDestinations
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun MyPageScreen(
    navController: NavController, viewModel: MyPageViewModel = hiltViewModel()
) {
    MainLayout(
        header = {
            MyPageScreenHeader(navController)
        },
        content = {
            MyPageScreenContent(navController)
            /*
            Button(
                onClick = {
                    viewModel.signOutUser()
                }
            ) {
                Text(text = "유저 탈퇴하기")
            }
             */
        })
}

@Composable
fun MyPageScreenHeader(navController: NavController) {
    BasicAppBar(modifier = Modifier
        .background(Background100)
        .height(48.dp)
        .padding(start = 20.dp, end = 20.dp)
        .fillMaxWidth(), rightSlot = {
        Icon(
            painter = painterResource(R.drawable.icon_notice),
            contentDescription = "icon_notice",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = {
                    navController.navigate(MyPageDestinations.ALARM)
                }),
            tint = Gray200
        )
    })
}

@Composable
fun MyPageScreenContent(navController: NavController) {
    Column(
        modifier = Modifier.padding()
    ) {
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Image(
                painter = painterResource(R.drawable.icon_connexion),
                contentDescription = "",
                modifier = Modifier.size(68.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Image(
                    painter = painterResource(R.drawable.tellingme_plus_box),
                    contentDescription = "",

                    )
                Text(
                    text = "듀이듀이",
                    modifier = Modifier.padding(top = 4.dp),
                    style = TellingmeTheme.typography.head3Bold
                )
            }
        }
        Box(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp)) {
            LevelSection(level = 1, percent = 50)
        }

        Column {
            Image(painter = painterResource(R.drawable.icon_bell), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_call), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_cheese), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_decoration), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_disk), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_report), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_subtract), contentDescription = "")
        }

    }


}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(navController = rememberNavController())
}