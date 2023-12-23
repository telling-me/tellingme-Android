package com.tellingus.tellingme.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tellingus.tellingme.R
import javax.annotation.concurrent.Immutable

private val nanumSquareRoundStyle = FontFamily(
    Font(R.font.nanum_square_round_regular, weight = FontWeight.Normal),
    Font(R.font.nanum_square_round_bold, weight = FontWeight.Bold)
)

internal val Typography = TellingmeTypography(
    h1Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    h1Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
)

@Immutable
data class TellingmeTypography(
    val h1Regular: TextStyle,
    val h1Bold: TextStyle
)

val LocalTellingmeTypography = staticCompositionLocalOf {
    TellingmeTypography(
        h1Regular = TextStyle.Default,
        h1Bold = TextStyle.Default
    )
}