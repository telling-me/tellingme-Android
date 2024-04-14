package com.tellingus.tellingme.presentation.ui.feature.otherspace.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.card.OpinionCard
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.component.section.QuestionSection
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.model.ButtonState
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import kotlinx.coroutines.launch

@Composable
fun OtherSpaceDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    var isComplaintBottomSheetOpen by remember { mutableStateOf(false) }

    MainLayout(header = {
        BasicAppBar(modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 12.dp), leftSlot = {
            TellingmeIconButton(iconRes = R.drawable.icon_caret_left,
                size = ButtonSize.MEDIUM,
                color = Gray500,
                onClick = {
                    navController.popBackStack()
                })
        }, rightSlot = {
            TellingmeIconButton(iconRes = R.drawable.icon_more,
                size = ButtonSize.MEDIUM,
                color = Gray500,
                onClick = {
                    isComplaintBottomSheetOpen = true
                })
        })
    }, content = {
        OtherSpaceDetailScreenContent()
        if (isComplaintBottomSheetOpen) {
            ComplaintBottomSheet(onDismiss = { isComplaintBottomSheetOpen = false })
        }
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintBottomSheet(onDismiss: () -> Unit = {}) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier.height(138.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.5.dp, bottom = 17.5.dp, start = 12.dp)
                .clickable(enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        scope
                            .launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    // TODO: 신고하기 기능
                                    onDismiss();
                                }
                            }
                    }),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.icon_siren
                ), contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("신고하기")
        }
    }


}

@Composable
fun OtherSpaceDetailScreenContent() {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 20.dp)
            .fillMaxHeight()
    ) {
        QuestionSection(
            title = "지금까지의 나의 인생을 두 단계로\n" + "나눈다면 어느 시점에 구분선을 둘 건가요?",
            description = "스스로 크게 변화한 시점을 떠올려봐요.",
            isButtonVisible = false,
            bgColor = Background100
        )
        OpinionCard(
            heartCount = mockData.heartCount,
            buttonState = mockData.buttonState,
            feeling = mockData.feeling,
            description = mockData.description,
            type = "full"
        )
    }
}

@Preview
@Composable
fun OtherSpaceDetailScreenPreview() {
    OtherSpaceDetailScreen(navController = rememberNavController())
}

@Preview
@Composable
fun ComplaintBottomSheetPreview() {
    ComplaintBottomSheet(onDismiss = {})
}


data class OpinionItem(
    val id: Int,
    val heartCount: Int,
    val buttonState: ButtonState,
    val feeling: String,
    val description: String
)

val mockData = OpinionItem(
    id = 1,
    heartCount = 999,
    buttonState = ButtonState.ENABLED,
    feeling = "excited",
    description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
)