package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.provider.useNavControllerContext

@Composable
fun AlarmScreen(modifier: Modifier = Modifier) {

    val navController = useNavControllerContext.current

    MainLayout(
        header = {
            BasicAppBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 5.dp, bottom = 5.dp, end = 10.dp),
                leftSlot = {
                    TellingmeIconButton(
                        iconRes = R.drawable.icon_caret_left,
                        size = ButtonSize.MEDIUM,
                        color = Gray500,
                        onClick = { navController?.popBackStack() }
                    )
                },
            )
        },
        content = { /*TODO*/ }
    )
}

@Preview
@Composable
fun AlarmScreenPreview() {
    AlarmScreen()
}