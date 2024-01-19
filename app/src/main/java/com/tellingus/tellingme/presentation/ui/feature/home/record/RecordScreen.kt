package com.tellingus.tellingme.presentation.ui.feature.home.record

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.button.BUTTON_SIZE
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryLightButton
import com.tellingus.tellingme.presentation.ui.common.button.SingleButton
import com.tellingus.tellingme.presentation.ui.common.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.dialog.ShowDoubleButtonDialog
import com.tellingus.tellingme.presentation.ui.common.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.widget.TOOLTIP_TYPE
import com.tellingus.tellingme.presentation.ui.common.widget.ToolTip
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Gray700
import com.tellingus.tellingme.presentation.ui.theme.Gray800
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecordScreen(
    modifier: Modifier = Modifier,
    navigateToPreviousScreen: () -> Unit
) {
    MainLayout(
        header = {
            RecordScreenHeader(modifier)
        },
        content = {
            RecordScreenContent(modifier)
        },
        isScrollable = false
    )
}

@Composable
fun RecordScreenHeader(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showDialogState by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 12.dp,
                top = 5.dp,
                bottom = 5.dp,
                end = 10.dp
            ),
    ) {
        TellingmeIconButton(
            modifier = modifier.align(Alignment.CenterStart),
            iconRes = R.drawable.icon_caret_left,
            size = BUTTON_SIZE.MEDIUM,
            color = Gray500,
            onClick = {
                Toast.makeText(context, "뒤로가기",Toast.LENGTH_SHORT).show()
            }
        )
        SingleButton(
            modifier = modifier.align(Alignment.CenterEnd),
            size = BUTTON_SIZE.LARGE,
            text = "완료",
            onClick = { showDialogState = !showDialogState }
        )

        if (showDialogState) {
            ShowDoubleButtonDialog(
                modifier = modifier,
                title = "글을 등록할까요?",
                contents = "글을 등록하고 나면 감정을 바꿀 수 없어요.",
                leftButton = {
                    PrimaryLightButton(
                        modifier = Modifier.weight(1f),
                        size = BUTTON_SIZE.LARGE,
                        text = "취소",
                        onClick = { showDialogState = false }
                    )
                },
                rightButton = {
                    PrimaryButton(
                        modifier = Modifier.weight(1f),
                        size = BUTTON_SIZE.LARGE,
                        text = "완료",
                        onClick = {
                            Toast.makeText(context, "완료 후 홈으로 이동", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun RecordScreenContent(modifier: Modifier = Modifier) {
    var recordText by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(true) }
    var isEmotionBottomSheetOpen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(
                    top = 16.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 31.dp
                )
        ) {
            Text(
                text = "지금까지 나의 인생을 두 단계로\n나눈다면 어느 시점에 구분선을 둘 건가요?",
                style = TellingmeTheme.typography.head3Regular,
                color = Gray700
            )
            Spacer(modifier = modifier.size(8.dp))
            Text(
                text = "스스로 크게 변화한 시점을 떠올려봐요.",
                style = TellingmeTheme.typography.body2Regular,
                color = Gray500
            )
            Spacer(modifier = modifier.size(16.dp))

            Card(
                modifier = modifier.fillMaxSize(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(Base0)
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth()
//                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { isEmotionBottomSheetOpen = true }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = modifier.size(44.dp),
                                painter = painterResource(R.drawable.emotion_circle),
                                contentDescription = null
                            )
                            Spacer(modifier = modifier.size(8.dp))
                            Text(
                                modifier = modifier
                                    .defaultMinSize(minWidth = 63.dp)
                                    .background(
                                        color = Background100,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 6.dp, vertical = 1.5.dp),
                                text = "?",
                                textAlign = TextAlign.Center,
                                style = TellingmeTheme.typography.body2Bold,
                                color = Gray600
                            )
                        }
                        Spacer(modifier = modifier.size(12.dp))
                        ToolTip(
                            modifier = modifier.padding(top = 4.dp),
                            type = TOOLTIP_TYPE.BASIC,
                            text = "감정을 선택해주세요!"
                        )
                    }
                    Spacer(modifier = modifier.size(12.dp))
                    Text(
                        text = "2023.08.26",
                        style = TellingmeTheme.typography.caption1Bold,
                        color = Gray300
                    )
                    Spacer(modifier = modifier.size(8.dp))

                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = recordText,
                        onValueChange = {
                            if (it.length <= 300) {
                                recordText = it
                            }
                        },
                        textStyle = TellingmeTheme.typography.body2Regular.copy(
                            color = Gray800,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Start
                        ),
                        decorationBox = { innerTextField ->
                            Box {
                                if (recordText.isBlank()) {
                                    Text(
                                        text = "여기를 눌러 작성해주세요!",
                                        style = TellingmeTheme.typography.body2Regular.copy(
                                            color = Gray300
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "공개",
                style = TellingmeTheme.typography.caption1Bold,
                color = Gray600
            )
            Spacer(modifier = modifier.size(10.dp))
            Switch(
                modifier = modifier.scale(0.8f),
                checked = switchState,
                onCheckedChange = { switchState = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Base0,
                    checkedTrackColor = Primary400
                ),
                interactionSource = remember { MutableInteractionSource() }
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "${recordText.length} / 300",
                style = TellingmeTheme.typography.caption1Bold,
                color = Gray600
            )
        }
    }

    if (isEmotionBottomSheetOpen) {
        EmotionBottomSheet(
            onDismiss = { isEmotionBottomSheetOpen = false },
            onClickCancel = { isEmotionBottomSheetOpen = false },
            onClickConfirm = { isEmotionBottomSheetOpen = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmotionBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
) {
    val emotionList = listOf<Int>(
        R.drawable.emotion_happy_large,
        R.drawable.emotion_proud_large,
        R.drawable.emotion_meh_large,
        R.drawable.emotion_tired_large,
        R.drawable.emotion_sad_large,
        R.drawable.emotion_angry_large,
    )
//    val sheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = false,
//        confirmValueChange = {false}
//    )
    val sheetState = rememberModalBottomSheetState()
    var selectedEmotion by remember { mutableStateOf(-1) }
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Background100,
        dragHandle = null
    ) {
        Column(
            modifier = modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = "이 글 속 나의 감정을 떠올려 봐요",
                style = TellingmeTheme.typography.head3Bold,
                color = Gray600
            )
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = "듀이 감정티콘을 선택해주세요",
                style = TellingmeTheme.typography.body1Regular,
                color = Gray600
            )
            Spacer(modifier = modifier.size(16.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(Base0),
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    itemsIndexed(emotionList) {position, item ->
                        Image(
                            imageVector = ImageVector.vectorResource(item),
                            contentDescription = "emotion",
                            modifier = modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        selectedEmotion = position
                                    }
                                )
                                .alpha(
                                    if (selectedEmotion == -1 || position == selectedEmotion) {
                                        1f
                                    } else {
                                        0.5f
                                    }
                                )
                        )
                    }
                }
            }
            Spacer(modifier = modifier.size(16.dp))
            Row {
                PrimaryLightButton(
                    modifier = modifier.weight(1f),
                    size = BUTTON_SIZE.LARGE,
                    text = "취소",
                    onClick = onClickCancel
                )
                Spacer(modifier = modifier.size(8.dp))
                PrimaryButton(
                    modifier = modifier.weight(1f),
                    size = BUTTON_SIZE.LARGE,
                    text = "완료",
                    onClick = onClickConfirm
                )
            }
        }
    }
}

@Preview
@Composable
fun RecordScreenPreview() {
    RecordScreen {}
}