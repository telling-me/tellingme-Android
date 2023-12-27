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
    display1Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 48.sp
    ),
    display2Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 42.sp
    ),
    head1Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 36.sp
    ),
    head1Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 36.sp
    ),
    head2Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 30.sp
    ),
    head2Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 30.sp
    ),
    head3Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 27.sp
    ),
    head3Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 27.sp
    ),
    body1Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body1Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body2Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    body2Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    caption1Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    caption1Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    caption2Bold = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 15.sp
    ),
    caption2Regular = TextStyle(
        fontFamily = nanumSquareRoundStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 15.sp
    )
)

@Immutable
data class TellingmeTypography(
    val display1Bold: TextStyle,
    val display2Bold: TextStyle,
    val head1Bold: TextStyle,
    val head1Regular: TextStyle,
    val head2Bold: TextStyle,
    val head2Regular: TextStyle,
    val head3Bold: TextStyle,
    val head3Regular: TextStyle,
    val body1Bold: TextStyle,
    val body1Regular: TextStyle,
    val body2Bold: TextStyle,
    val body2Regular: TextStyle,
    val caption1Bold: TextStyle,
    val caption1Regular: TextStyle,
    val caption2Bold: TextStyle,
    val caption2Regular: TextStyle
)

val LocalTellingmeTypography = staticCompositionLocalOf {
    TellingmeTypography(
        display1Bold = TextStyle.Default,
        display2Bold = TextStyle.Default,
        head1Bold = TextStyle.Default,
        head1Regular = TextStyle.Default,
        head2Bold = TextStyle.Default,
        head2Regular = TextStyle.Default,
        head3Bold = TextStyle.Default,
        head3Regular = TextStyle.Default,
        body1Bold = TextStyle.Default,
        body1Regular = TextStyle.Default,
        body2Bold = TextStyle.Default,
        body2Regular = TextStyle.Default,
        caption1Bold = TextStyle.Default,
        caption1Regular = TextStyle.Default,
        caption2Bold = TextStyle.Default,
        caption2Regular = TextStyle.Default
    )
}