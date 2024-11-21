package br.com.connectattoo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import connectattoo.composeapp.generated.resources.Raleway_Black
import connectattoo.composeapp.generated.resources.Raleway_Bold
import connectattoo.composeapp.generated.resources.Raleway_ExtraBold
import connectattoo.composeapp.generated.resources.Raleway_Medium
import connectattoo.composeapp.generated.resources.Raleway_Regular
import connectattoo.composeapp.generated.resources.Raleway_SemiBold
import connectattoo.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalResourceApi::class)
@Composable
fun RalewayFontFamily() = FontFamily(
    Font(Res.font.Raleway_Black, weight = FontWeight.Black),
    Font(Res.font.Raleway_Regular, weight = FontWeight.Normal),
    Font(Res.font.Raleway_Bold, weight = FontWeight.Bold),
    Font(Res.font.Raleway_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.Raleway_ExtraBold, weight = FontWeight.ExtraBold),
)

@Composable
fun RalewayTypography() = Typography().run {
    val fontFamily = RalewayFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )

}