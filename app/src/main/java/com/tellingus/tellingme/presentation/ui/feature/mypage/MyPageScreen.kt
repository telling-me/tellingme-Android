package com.tellingus.tellingme.presentation.ui.feature.mypage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.component.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.util.AppUtils


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
            painter = painterResource(R.drawable.icon_setting),
            contentDescription = "icon_notice",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = {
//                    navController.navigate(MyPageDestinations.설정)
//                    [5-2. 설정] 이동
                }),
            tint = Gray200
        )
    })
}

@Composable
fun MyPageScreenContent(navController: NavController) {
    val context = LocalContext.current

    val items = remember {
        mutableStateOf(
            listOf(
                IconTextItem(
                    id = "terms_of_use",
                    iconResId = R.drawable.icon_terms_of_use,
                    text = "이용 약관",
                    actionType = ActionType.OPEN_URL,
                    destination = "https://doana.notion.site/f42ec05972a545ce95231f8144705eae?pvs=4"
                ),
                IconTextItem(
                    id = "privacy_policy",
                    iconResId = R.drawable.icon_privacy_policy,
                    text = "개인정보 처리방침",
                    actionType = ActionType.OPEN_URL,
                    destination = "https://doana.notion.site/7cdab221ee6d436781f930442040d556?pvs=4"
                ),
                IconTextItem(
                    id = "customer_service",
                    iconResId = R.drawable.icon_customer_service,
                    text = "고객센터",
                    actionType = ActionType.SEND_EMAIL,
                    destination = "Email"
                ),
                IconTextItem(
                    id = "tellingme_introduce",
                    iconResId = R.drawable.icon_planet,
                    text = "텔링미를 소개해요",
                    actionType = ActionType.NAVIGATE_SCREEN,
                    destination = "TellingMeScreen"
                ),
                IconTextItem(
                    id = "tellingme_instagram",
                    iconResId = R.drawable.icon_instagram,
                    text = "텔링미 인스타그램",
                    actionType = ActionType.OPEN_URL,
                    destination = "https://www.instagram.com/tellingme.io/"
                ),
            )
        )
    }

    var checked by remember { mutableStateOf(true) }

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
                        painter = painterResource(R.drawable.icon_badge), contentDescription = ""
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
                        painter = painterResource(R.drawable.icon_pencil), contentDescription = ""
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
                    painter = painterResource(R.drawable.icon_question_factory),
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
                    painter = painterResource(R.drawable.icon_faq),
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
                ) {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.icon_bell), contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "푸시 알림 받기")
                    }

                    Switch(checked = checked, onCheckedChange = {
                        checked = it
                    })
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

                                    ActionType.SEND_EMAIL -> {
                                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                            data = Uri.parse("mailto:")
                                            putExtra(
                                                Intent.EXTRA_EMAIL,
                                                arrayOf("tellingmetime@gmail.com")
                                            )
                                            putExtra(
                                                Intent.EXTRA_CC, arrayOf("crin1224@icloud.com")
                                            )
                                            putExtra(Intent.EXTRA_SUBJECT, "[텔링미 고객센터] 전달사항이 있어요!")
                                            putExtra(Intent.EXTRA_TEXT, getEmailBody(context))
                                        }
                                        context.startActivity(
                                            Intent.createChooser(
                                                emailIntent, "[텔링미 고객센터] 전달사항이 있어요!"
                                            )
                                        )
                                    }
                                }
                            }) {
                        Row {
                            Image(
                                painter = painterResource(id = item.iconResId),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)

                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.text, style = TellingmeTheme.typography.body2Regular)
                        }

                        Row {
                            if (item.id === "tellingme_introduce") {
                                Text(text = "v. 1.3.7", style = TellingmeTheme.typography.body2Bold)
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
        }
    }
}


fun getEmailBody(context: Context): String {
    val appVersion = AppUtils.getAppVersion(context)
    return "안녕하세요, 텔링미입니다.\n어떤 내용을 텔링미에게 전달하고 싶으신가요? 자유롭게 작성해주시면 확인 후 답변드리겠습니다. 감사합니다.😃\n📲 쓰고 있는 핸드폰 기종 (예:아이폰 12) : \n\n🧭 앱 버전 : ${appVersion}\n🧗 닉네임 : \n\n⚠️ 오류를 발견하셨을 경우 ⚠️\n📍발견한 오류 : \n📷 오류 화면 (캡쳐 혹은 화면녹화) : \n"
}

enum class ActionType {
    OPEN_URL, NAVIGATE_SCREEN, SEND_EMAIL
}

data class IconTextItem(
    val id: String,
    val iconResId: Int,
    val text: String,
    val actionType: ActionType,
    val destination: String
)

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(navController = rememberNavController())
}