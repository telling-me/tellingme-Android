package com.tellingus.tellingme.presentation.ui.common.component.box

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun SelectBox(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    text: String,
    textStyle: TextStyle = TellingmeTheme.typography.body1Regular,
    @DrawableRes icon: Int? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSelected) Primary400 else Gray200,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                id = if (isSelected) R.drawable.icon_check_selected else R.drawable.icon_check_unselected
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
}

@Preview(showBackground = true)
@Composable
fun SelectBoxPreview() {
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