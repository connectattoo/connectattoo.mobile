package br.com.connectattoo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val black: Color,
    val black100: Color,
    val black200: Color,
    val black500: Color,
    val black800: Color,
    val black900: Color,
    val black50: Color,
    val white: Color,
    val red: Color,
    val orange: Color,
    val deepPurple: Color,
    val purple50: Color,
    val purple100: Color,
    val purple300: Color,
    val purple500: Color,
    val purple800: Color,
    val purple700: Color,
    val purple900: Color,
    val gray400: Color,
    val green: Color,
    val black25: Color
)

val LocalExtendedColors = staticCompositionLocalOf<ExtendedColors> {
    error("No ExtendedColors provided")
}
val LightExtendedColors = ExtendedColors(
    black = Color(0xFF000000),
    black100 = Color(0xFFC8C8C8),
    black200 = Color(0xFFACACAC),
    black500 = Color(0xFF757575),
    black800 = Color(0xFF2D2D2D),
    black900 = Color(0xFF1E1E1E),
    black50 = Color(0xFFE4E4E4),
    white = Color(0xFFFFFFFF),
    red = Color(0xFFEF5350),
    orange = Color(0xFFFB6500),
    deepPurple = Color(0xFF673AB7),
    purple50 = Color(0xFFF8F2FF),
    purple100 = Color(0xFFEBD7FF),
    purple300 = Color(0xFFC388FD),
    purple500 = Color(0xFF7A32C1),
    purple800 = Color(0xFF460D7D),
    purple700 = Color(0xFF601EA1),
    purple900 = Color(0xFF30045C),
    gray400 = Color(0xFFCBC9CA),
    green = Color(0xFF037D00),
    black25 = Color(0xFFEFEFEF)
)

val DarkExtendedColors = ExtendedColors(
    black = Color(0xFF000000),
    black100 = Color(0xFFC8C8C8),
    black200 = Color(0xFFACACAC),
    black500 = Color(0xFF757575),
    black800 = Color(0xFF2D2D2D),
    black900 = Color(0xFF1E1E1E),
    black50 = Color(0xFFE4E4E4),
    white = Color(0xFFFFFFFF),
    red = Color(0xFFEF5350),
    orange = Color(0xFFFB6500),
    deepPurple = Color(0xFF673AB7),
    purple50 = Color(0xFFF8F2FF),
    purple100 = Color(0xFFEBD7FF),
    purple300 = Color(0xFFC388FD),
    purple500 = Color(0xFF7A32C1),
    purple800 = Color(0xFF460D7D),
    purple700 = Color(0xFF601EA1),
    purple900 = Color(0xFF30045C),
    gray400 = Color(0xFFCBC9CA),
    green = Color(0xFF037D00),
    black25 = Color(0xFFEFEFEF)
)
val LightColors: ColorScheme
    @Composable get() = lightColorScheme(
        primary = Color(0xFF7A32C1), // purple500
        secondary = Color(0xFFFB6500), // orange
        background = Color(0xFFE4E4E4), // black50
        surface = Color(0xFFFFFFFF),
        error = Color(0xFFEF5350),
        onPrimary = Color(0xFF7A32C1),
        onSecondary = Color(0xFFFFFFFF),
        onBackground = Color(0xFF201A1B),
        onSurface = Color(0x99000000),
        onError = Color(0xFFFFFFFF),
    )

val DarkColors: ColorScheme
    @Composable get() = darkColorScheme(
        primary = Color(0xFF7A32C1), // purple500
        secondary = Color(0xFFFF4081),
        background = Color(0xFF1E1E1E), // black900
        surface = Color(0xFF2D2D2D), // black800
        error = Color(0xFFEF5350), // red
        onPrimary = Color(0xFFFFFFFF),
        onSecondary = Color(0xFFFFFFFF),
        onBackground = Color(0xFFFFFFFE),
        onSurface = Color(0xFFFFFFFF),
        onError = Color(0xFFFFFFFF),
    )

@Composable
fun ConnectattooTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colors,
            shapes = Shapes,
            content = content,
        )
    }
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    get() = LocalExtendedColors.current
