package com.tellingus.tellingme.presentation.ui.feature.mypage

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun MyPageScreen(
    navController: NavController, viewModel: MyPageViewModel = hiltViewModel()
) {
    MainLayout(header = {
        MyPageScreenHeader(navController)
    }, content = {
        MyPageScreenContent(navController)/*
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
    val items = remember {
        mutableStateOf(
            listOf(
                IconTextItem(
                    iconResId = R.drawable.icon_subtract,
                    text = "이용 약관",
                    actionType = ActionType.OPEN_URL,
                    destination = "https://doana.notion.site/f42ec05972a545ce95231f8144705eae?pvs=4"
                ),
                IconTextItem(
                    iconResId = R.drawable.icon_report,
                    text = "개인정보 처리방침",
                    actionType = ActionType.OPEN_URL,
                    destination = "https://doana.notion.site/7cdab221ee6d436781f930442040d556?pvs=4"
                ),
                IconTextItem(
                    iconResId = R.drawable.icon_call,
                    text = "고객센터",
                    actionType = ActionType.SEND_EMAIL,
                    destination = "Email"
                ),
                IconTextItem(
                    iconResId = R.drawable.icon_disk,
                    text = "텔링미에 대해서",
                    actionType = ActionType.NAVIGATE_SCREEN,
                    destination = "TellingMeScreen"
                ),
                IconTextItem(
                    iconResId = R.drawable.icon_heart,
                    text = "텔링미 인스타그램",
                    actionType = ActionType.OPEN_URL,
                    destination = "https://www.instagram.com/tellingme.io/"
                ),
            )
        )
    }


    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        Row {
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
        Box(modifier = Modifier.padding(top = 10.dp)) {
            LevelSection(level = 1, percent = 50)
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(Base0),
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround // Spacing between columns
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_cheese), contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "치즈",
                        color = Gray600,
                        style = TellingmeTheme.typography.caption2Regular
                    )
                    Text(
                        text = "378", color = Gray600, style = TellingmeTheme.typography.body2Bold
                    )
                }

                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(54.dp)
                        .background(Gray200)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_decoration),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "배지",
                        color = Gray600,
                        style = TellingmeTheme.typography.caption2Regular
                    )
                    Text(
                        text = "24", color = Gray600, style = TellingmeTheme.typography.body2Bold
                    )
                }

                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(54.dp)
                        .background(Gray200)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_cheese), contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "작성글수",
                        color = Gray600,
                        style = TellingmeTheme.typography.caption2Regular
                    )
                    Text(
                        text = "346", color = Gray600, style = TellingmeTheme.typography.body2Bold
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .height(78.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(Color(0xFF93A0FF)),
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp, end = 13.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.tellingme_plus_circle),
                        contentDescription = "",
                        modifier = Modifier.size(42.dp)
                    )
                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Text(
                            text = "텔링미 구독 서비스",
                            color = Base0,
                            style = TellingmeTheme.typography.caption2Regular
                        )
                        Text(
                            text = "PLUS 라운지 입장하기",
                            color = Base0,
                            style = TellingmeTheme.typography.body2Bold
                        )
                    }
                }
                Icon(
                    painter = painterResource(id = R.drawable.icon_caret_right),
                    contentDescription = "",
                    tint = Base0
                )
            }
        }


        Row(modifier = Modifier.padding(top = 12.dp)) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(74.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_bell),
                    contentDescription = "",
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(text = "듀이의 질문\n" + "제작소")
                Icon(
                    painter = painterResource(id = R.drawable.icon_caret_right),
                    contentDescription = "",
                    tint = Gray500,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            Spacer(modifier = Modifier.width(11.dp))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(74.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_bell),
                    contentDescription = "",
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(text = "자주 묻는\n" + "질문")
                Icon(
                    painter = painterResource(id = R.drawable.icon_caret_right),
                    contentDescription = "",
                    tint = Gray500,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
                .background(Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp)
                        .padding(horizontal = 12.dp)
                        .background(Color.White)
                        .clickable { }
                ) {

                    Row {
                        Image(
                            painter = painterResource(R.drawable.icon_bell),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "푸시 알림 받기")
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.icon_caret_right),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }

                val context = LocalContext.current

                items.value.forEach { item ->
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(53.dp)
                            .padding(horizontal = 12.dp)
                            .background(Color.White)
                            .clickable {
                                when (item.actionType) {
                                    ActionType.OPEN_URL -> {
                                        val intent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(item.destination))
                                        context.startActivity(intent)
                                    }

                                    ActionType.NAVIGATE_SCREEN -> {
                                        // navController.navigate(item.destination)
                                    }

                                    ActionType.SEND_EMAIL -> {}
                                }
                            }) {
                        Row {
                            Image(
                                painter = painterResource(id = item.iconResId),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)

                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.text)
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.icon_caret_right),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        Column {
            Image(painter = painterResource(R.drawable.icon_bell), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_call), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_disk), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_report), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_subtract), contentDescription = "")
            Image(painter = painterResource(R.drawable.icon_heart), contentDescription = "")
            Icon(
                painter = painterResource(id = R.drawable.icon_caret_right),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

enum class ActionType {
    OPEN_URL, NAVIGATE_SCREEN, SEND_EMAIL
}

data class IconTextItem(
    val iconResId: Int, val text: String, val actionType: ActionType, val destination: String
)

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(navController = rememberNavController())
}