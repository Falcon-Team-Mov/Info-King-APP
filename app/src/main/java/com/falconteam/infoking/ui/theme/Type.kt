package com.falconteam.infoking.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.falconteam.infoking.R

val jostBold = FontFamily(Font(R.font.jost_bold))
val jostBoldItalic = FontFamily(Font(R.font.jost_bolditalic))
val jostExtraBold = FontFamily(Font(R.font.jost_extrabold))
val jostLight = FontFamily(Font(R.font.jost_light))
val jostMedium = FontFamily(Font(R.font.jost_medium))
val jostMediumItalic = FontFamily(Font(R.font.jost_mediumitalic))
val jostRegular = FontFamily(Font(R.font.jost_regular))
val jostSemiBold = FontFamily(Font(R.font.jost_semibold))
val jostSemiBoldItalic = FontFamily(Font(R.font.jost_semibolditalic))

// Set of Material typography styles to start with
val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = jostBold,
        fontSize = 57.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displayMedium = TextStyle(
        fontFamily = jostSemiBold,
        fontSize = 45.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displaySmall = TextStyle(
        fontFamily = jostSemiBold,
        fontSize = 36.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = jostSemiBold,
        fontSize = 32.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = jostSemiBold,
        fontSize = 28.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = jostSemiBold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = jostSemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = jostMedium,
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodySmall = TextStyle(
        fontFamily = jostRegular,
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
)