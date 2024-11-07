package br.com.connectattoo.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val snowWhite: Color,
    val deepOcean: Color,
    val skyBlue: Color,
    val nightBlue: Color,
    val dialogBackground: Color
)

val LocalExtendedColors = staticCompositionLocalOf<ExtendedColors> {
    error("No ExtendedColors provided")
}

val LightExtendedColors = ExtendedColors(
    snowWhite = Color(0xFFFFFFFF),
    deepOcean = Color(0xFF001B2E),
    skyBlue = Color(0xFF81D4FA),
    nightBlue = Color(0xFF102027),
    dialogBackground = Color(0xFFF1F1F1)
)

val DarkExtendedColors = ExtendedColors(
    snowWhite = Color(0xFF9E9E9E),
    deepOcean = Color(0xFF001B2E),
    skyBlue = Color(0xFF4FC3F7),
    nightBlue = Color(0xFF000A12),
    dialogBackground = Color(0xFF303030)
)

val LightColors = Colors(
    primary = Color(0xFF7C54A7),
    primaryVariant = Color(0xFF512DA8),
    secondary = Color(0xFFB90063),
    secondaryVariant = Color(0xFF7B1FA2),
    background = Color(0xFFFAFAFA),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFFF917A),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF201A1B),
    onSurface = Color(0x99000000),
    onError = Color(0xFFFFFFFF),
    isLight = true
)

val DarkColors = Colors(
    primary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFFB90063),
    secondary = Color(0xFFFF4081),
    secondaryVariant = Color(0xFF512DA8),
    background = Color(0xFF9A0963),
    surface = Color(0xFF77084D),
    error = Color(0xFFFF917A),
    onPrimary = Color(0xFFB90063),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFFFFFE),
    onSurface = Color(0xFFFFFFFF),
    onError = Color(0xFFFFFFFF),
    isLight = false
)

@Composable
fun ConnectattooTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colors = colors,
            shapes = Shapes,
            content = content
        )
    }
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    get() = LocalExtendedColors.current
