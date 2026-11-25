package edu.unicauca.orderbot_multi.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.model.ModoTema

private val ColoresClaros = lightColorScheme(
    primary = OrderBotColors.AzulOrderBot,
    onPrimary = Color.White,
    primaryContainer = OrderBotColors.AzulClaroOrderBot,
    onPrimaryContainer = OrderBotColors.TextoPrincipal,
    secondary = OrderBotColors.TextoSecundario,
    onSecondary = Color.White,
    secondaryContainer = OrderBotColors.AzulClaroOrderBot,
    onSecondaryContainer = OrderBotColors.TextoPrincipal,
    tertiary = OrderBotColors.VerdeOrderBot,
    tertiaryContainer = OrderBotColors.VerdeClaroOrderBot,
    onTertiaryContainer = OrderBotColors.TextoPrincipal,
    background = OrderBotColors.FondoOrderBot,
    onBackground = OrderBotColors.TextoPrincipal,
    surface = Color.White,
    onSurface = OrderBotColors.TextoPrincipal,
    onSurfaceVariant = OrderBotColors.TextoSecundario,
    outline = OrderBotColors.TextoSecundario,
)

private val ColoresOscuros = darkColorScheme(
    primary = OrderBotColors.AzulOrderBotOscuro,
    onPrimary = OrderBotColors.TextoPrincipal,
    primaryContainer = OrderBotColors.AzulContenedorOscuro,
    onPrimaryContainer = OrderBotColors.TextoPrincipalOscuro,
    secondary = OrderBotColors.TextoSecundarioOscuro,
    onSecondary = OrderBotColors.TextoPrincipal,
    secondaryContainer = OrderBotColors.AzulContenedorOscuro,
    onSecondaryContainer = OrderBotColors.TextoPrincipalOscuro,
    tertiary = OrderBotColors.VerdeOrderBotOscuro,
    tertiaryContainer = OrderBotColors.VerdeContenedorOscuro,
    onTertiaryContainer = OrderBotColors.TextoPrincipalOscuro,
    background = OrderBotColors.FondoOscuro,
    onBackground = OrderBotColors.TextoPrincipalOscuro,
    surface = OrderBotColors.SuperficieOscura,
    onSurface = OrderBotColors.TextoPrincipalOscuro,
    onSurfaceVariant = OrderBotColors.TextoSecundarioOscuro,
    outline = OrderBotColors.TextoSecundarioOscuro,
)

private val Formas = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(18.dp),
)

/** Equivale a `OrderBotTema` + `preferredColorScheme` de SwiftUI. */
@Composable
fun OrderBotTheme(
    modoTema: ModoTema = ModoTema.SISTEMA,
    content: @Composable () -> Unit,
) {
    val oscuro = when (modoTema) {
        ModoTema.SISTEMA -> isSystemInDarkTheme()
        ModoTema.CLARO -> false
        ModoTema.OSCURO -> true
    }

    MaterialTheme(
        colorScheme = if (oscuro) ColoresOscuros else ColoresClaros,
        shapes = Formas,
        content = content,
    )
}
