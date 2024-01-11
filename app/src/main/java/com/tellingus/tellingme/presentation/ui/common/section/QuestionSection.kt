package com.tellingus.tellingme.presentation.ui.common.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.common.button.BUTTON_SIZE
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun QuestionSection(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClickButton: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 208.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.5.dp, start = 22.dp, end = 22.dp, bottom = 17.5.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(Gray50, shape = RoundedCornerShape(8.dp))
                    .size(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Q",
                    style = TellingmeTheme.typography.body1Bold,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                modifier = Modifier.padding(top = 17.5.dp),
                text = title,
                style = TellingmeTheme.typography.body1Bold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = description,
                style = TellingmeTheme.typography.body2Regular,
                textAlign = TextAlign.Center
            )

            PrimaryButton(
                modifier = Modifier.padding(top = 28.dp),
                size = BUTTON_SIZE.MEDIUM,
                text = "기록하기",
                onClick = onClickButton
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionSectionPreview() {
    Column {
        QuestionSection(title = "제목을 입력해주세요", description = "내용을 입력해주세요", onClickButton = {})
    }
}