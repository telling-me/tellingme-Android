package com.tellingus.tellingme.presentation.ui.common.component.box

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.util.noRippleClickable

@Composable
fun CheckBox(
    text: String,
    onClick: () -> Unit,
    url: String = "",
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    showRightIcon: Boolean = false
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 14.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                id = if (isSelected) R.drawable.icon_check_box_on else R.drawable.icon_check_box_off
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            style = TellingmeTheme.typography.body2Regular,
            color = Gray600
        )
        if (showRightIcon) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
            Image(
                modifier = modifier
                    .size(20.dp)
                    .noRippleClickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    },
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_caret_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Gray500)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    Column {
        CheckBox(
            text = "Text",
            onClick = {  },
            isSelected = false
        )
        CheckBox(
            text = "Text",
            onClick = {  },
            isSelected = true
        )
        CheckBox(
            text = "Text",
            onClick = {  },
            isSelected = false,
            showRightIcon = true
        )
    }
}