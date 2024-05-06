package com.tellingus.tellingme.presentation.ui.common.component.box

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun SelectBoxWithTextField(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    text: String,
    contents: String = "",
    onChangeTextField: (String) -> Unit,
    onClick : () -> Unit = {},
    textStyle: TextStyle = TellingmeTheme.typography.body1Regular,
    @DrawableRes icon: Int? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSelected) Primary400 else Gray200,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(
                    id = if (isSelected) R.drawable.icon_check_circle_on else R.drawable.icon_check_circle_off
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(12.dp))
            icon?.let {
                Image(
                    imageVector = ImageVector.vectorResource(id = it),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
            Text(
                text = text,
                style = textStyle.copy(
                    color = Gray600
                ),
            )
        }

        if (isSelected) {
            Spacer(modifier = modifier.size(16.dp))

            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                value = contents,
                onValueChange = {
                    onChangeTextField(it)
                },
                textStyle = TellingmeTheme.typography.body1Regular.copy(
                    color = Gray600,
                    textAlign = TextAlign.Start
                ),
                maxLines = 1,
                singleLine = true,
                decorationBox = { innerTextField ->
                    Column {
                        Box {
                            Box(
                                modifier = modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                                androidx.compose.material.Text(
                                    text = if (contents.isBlank()) "하시는 일을 간단히 입력해주세요." else " ",
                                    style = TellingmeTheme.typography.body1Regular.copy(
                                        color = Gray300
                                    )
                                )
                                if (contents.isNotBlank()) {
                                    Icon(
                                        modifier = modifier
                                            .align(Alignment.CenterEnd)
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null,
                                                onClick = {
                                                    onChangeTextField(contents)
                                                }
                                            ),
                                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_clear_text),
                                        contentDescription = null,
                                        tint = Gray300
                                    )
                                }
                            }
                        }
                        Spacer(modifier = modifier.size(8.dp))
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(if (isFocused) Primary400 else Gray200)
                                .height(1.dp)

                        )

                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectBoxWithTextFieldPreview() {
    Column {
        SelectBox(
            isSelected = true,
            text = "Text",
            icon = R.drawable.icon_bagpack
        )
        SelectBox(
            isSelected = false,
            text = "Text"
        )
    }
}