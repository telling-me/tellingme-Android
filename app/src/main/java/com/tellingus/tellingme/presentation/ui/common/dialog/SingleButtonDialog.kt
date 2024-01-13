package com.tellingus.tellingme.presentation.ui.common.dialog

import androidx.compose.foundation.layout.Column
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
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun SingleButtonDialog(
    modifier: Modifier = Modifier,
    title: String,
    contents: String,
    completeButton: @Composable () -> Unit
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
            completeButton()
        }
    }
}

@Preview
@Composable
fun SingleButtonDialogPreview() {
    SingleButtonDialog(
        title = "Title",
        contents = "텍스트",
        completeButton = {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                size = BUTTON_SIZE.LARGE,
                text = "완료",
                onClick = {}
            )
        }
    )
}