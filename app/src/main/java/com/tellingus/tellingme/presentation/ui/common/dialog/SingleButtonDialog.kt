package com.tellingus.tellingme.presentation.ui.common.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
                    start = 16.dp,
                    end = 16.dp
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

@Composable
fun ShowSingleButtonDialog(
    modifier: Modifier = Modifier,
    title: String,
    contents: String,
    completeButton: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            color = Base0
        ) {
            SingleButtonDialog(
                title = title,
                contents = contents,
                completeButton = completeButton
            )
        }
    }
}

@Preview
@Composable
fun SingleButtonDialogPreview() {
    ShowSingleButtonDialog(
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