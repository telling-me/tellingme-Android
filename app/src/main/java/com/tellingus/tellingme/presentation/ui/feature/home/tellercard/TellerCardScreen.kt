package com.tellingus.tellingme.presentation.ui.feature.home.tellercard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.badge.CheeseBadge
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.component.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.theme.Background100

@Composable
fun TellerCardScreen(
    navController: NavController,
) {
    MainLayout(header = { TellerScreenHeader(navController) }, content = { TellerScreenContent() })
}


@Composable
fun TellerScreenContent() {
    Column {
        Box(modifier = Modifier.padding(20.dp)) {
            MyTellerCard()
        }
        Box(modifier = Modifier.padding(20.dp)) {
            LevelSection(level = 1, percent = 22)
        }
        Box() {
            TellerBadgeList()
        }
    }
}

@Composable
fun TellerScreenHeader(navController: NavController) {
    BasicAppBar(
        modifier = Modifier
            .background(Background100)
            .height(48.dp)
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        leftSlot = {
            Icon(
                painter = painterResource(R.drawable.icon_caret_left),
                contentDescription = "tellingme_logo",
                modifier = Modifier.clickable(onClick = { navController.popBackStack() })
            )
        },
        rightSlot = { CheeseBadge() }
    )
}


@Preview(showBackground = true)
@Composable
fun TellerScreenHeaderPreview() {
    TellerScreenHeader(navController = rememberNavController())
}
