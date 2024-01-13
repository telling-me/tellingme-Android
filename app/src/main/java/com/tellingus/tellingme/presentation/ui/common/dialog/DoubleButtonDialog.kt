package com.tellingus.tellingme.presentation.ui.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.common.button.BUTTON_SIZE
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryButtonLargePreview
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryLightButton
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun DoubleButtonDialog(
    modifier: Modifier = Modifier,
    title: String,
    contents: String,
    rightButtonText: String,
    onClickRightButton: () -> Unit,
    leftButtonText: String,
    onClickLeftButton: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Base0),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Column(
            modifier = modifier
                .padding(
                    top = 30.dp,
                    bottom = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TellingmeTheme.typography.body1Bold.copy(
                    color = Gray600
                )
            )
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = contents,
                style = TellingmeTheme.typography.body2Regular.copy(
                    color = Gray600
                )
            )
            Spacer(modifier = modifier.size(20.dp))

            Row {
                PrimaryLightButton(
                    modifier = modifier.weight(1f),
                    size = BUTTON_SIZE.LARGE,
                    text = leftButtonText,
                    onClick = onClickLeftButton
                )
                Spacer(modifier = modifier.size(8.dp))
                PrimaryButton(
                    modifier = modifier.weight(1f),
                    size = BUTTON_SIZE.LARGE,
                    text = rightButtonText,
                    onClick = onClickRightButton
                )
            }
        }
    }

}

@Preview
@Composable
fun DoubleButtonDialogPreview() {
    DoubleButtonDialog(
        title = "Title",
        contents = "텍스트",
        rightButtonText = "완료",
        leftButtonText = "취소",
        onClickRightButton = {},
        onClickLeftButton = {}
    )
}