package com.tellingus.tellingme.presentation.ui.common.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.component.button.SingleButton
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun PushAlertDialog(
    modifier: Modifier = Modifier,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit
) {
    Dialog(
        onDismissRequest = { onClickNegative() },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White
                )
                .padding(top = 30.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
        ) {
            Text(
                modifier = modifier.padding(bottom = 4.dp),
                text = "하루에 한번 알림을 드려도 될까요?",
                style = TellingmeTheme.typography.body1Bold.copy(
                    color = Gray600,
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = modifier.padding(bottom = 12.dp),
                text = "매일 기록을 잊지 않게 해드릴게요!",
                style = TellingmeTheme.typography.body2Regular.copy(
                    color = Gray600,
                    fontSize = 14.sp
                )
            )
            Image(
                modifier = modifier.padding(bottom = 12.dp),
                imageVector = ImageVector.vectorResource(R.drawable.image_push_alert),
                contentDescription = null
            )

            PrimaryButton(
                size = ButtonSize.LARGE,
                text = "좋아요",
                onClick = {
                    onClickPositive()
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            SingleButton(
                size = ButtonSize.LARGE,
                text = "괜찮아요",
                onClick = {
                    onClickNegative()
                }
            )
        }
    }
}