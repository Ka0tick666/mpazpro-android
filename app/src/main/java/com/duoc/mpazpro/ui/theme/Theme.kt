package com.duoc.mpazpro.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val MpazProColorScheme = lightColorScheme(

    primary = PrimaryBlue,
    onPrimary = TextWhite,

    primaryContainer = LenguajeBackground,
    onPrimaryContainer = TextPrimary,

    secondary = SecondaryGreen,
    onSecondary = TextWhite,

    secondaryContainer = MatematicaBackground,
    onSecondaryContainer = TextPrimary,

    tertiary = TertiaryBlue,
    onTertiary = TextWhite,

    background = AppBackground,
    onBackground = TextPrimary,

    surface = SurfaceWhite,
    onSurface = TextPrimary,

    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = TextSecondary,

    outline = BorderColor,

    error = ErrorColor,
    onError = TextWhite
)

private val MpazProShapes = Shapes(

    extraSmall = RoundedCornerShape(6.dp),

    small = RoundedCornerShape(8.dp),

    medium = RoundedCornerShape(12.dp),

    large = RoundedCornerShape(16.dp),

    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun MpazProTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MpazProColorScheme,
        typography = Typography,
        shapes = MpazProShapes,
        content = content
    )
}