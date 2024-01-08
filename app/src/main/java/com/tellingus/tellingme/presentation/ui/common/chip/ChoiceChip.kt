package com.tellingus.tellingme.presentation.ui.common.chip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray100
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun ChoiceChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    enable: Boolean = true,
    onClick: (Boolean) -> Unit
) {
    Card(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            enabled = enable,
            onClick = {
                onClick(selected)
            }
        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            if (enable) {
                if (selected) Gray500 else Gray200
            } else Gray100
        )
    ) {
        Text(
            modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = text,
            style = TellingmeTheme.typography.body2Bold,
            color = if (enable) {
                if (selected) Base0 else Gray600
            } else Gray300
        )
    }
}

@Composable
fun ChoiceChips(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    onClick: (Int, Boolean) -> Unit
) {
    LazyRow(
        modifier = modifier
    ) {
        items(elements.size) {position ->
            ChoiceChip(
                text = elements[position].text,
                selected = elements[position].isSelected.value,
                enable = elements[position].enable.value,
                onClick = { isSelected ->
                    onClick(position, isSelected)
                }
            ) 
            Spacer(modifier = modifier.size(8.dp))
        }
    }
}

data class ChipState(
    val text: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false),
    val enable: MutableState<Boolean> = mutableStateOf(true)
)

@Preview
@Composable
fun ChoiceChipsPreview() {
    val elements by remember {
        mutableStateOf(
            listOf(
                ChipState("인기순", mutableStateOf(true)),
                ChipState("최신순"),
                ChipState("관련순")
            )
        )
    }

    ChoiceChips(
        elements = elements,
        onClick = { position, isSelected ->
            // 칩을 단일/다중 선택할 수 있도록 (일단은 단일 선택 칩)
            elements.map { it.isSelected.value = false }
            elements[position].isSelected.value = !isSelected
        }
    )
}

@Preview
@Composable
fun ChoiceChipSelectedPreview() {
    ChoiceChip(text = "Label", selected = true) { }
}

@Preview
@Composable
fun ChoiceChipEnabledPreview() {
    ChoiceChip(text = "Label", selected = false) { }
}

@Preview
@Composable
fun ChoiceChipDisabledPreview() {
    ChoiceChip(text = "Label", enable = false) { }
}