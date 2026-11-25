package edu.unicauca.orderbot_multi.android.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.model.ModoTema

private val AlturaBoton = 52.dp

@Composable
fun BotonPrimario(
    texto: String,
    habilitado: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        modifier = Modifier.fillMaxWidth().height(AlturaBoton),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Text(texto, style = TiposOrderBot.cuerpoDestacado)
    }
}

@Composable
fun BotonSecundario(texto: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(AlturaBoton),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.45f),
        ),
    ) {
        Text(texto, style = TiposOrderBot.cuerpo)
    }
}

/** Selector de tema: ☀️ claro, 🌙 oscuro, 🌗 automático. */
@Composable
fun BotonModoTema(modo: ModoTema, onClick: () -> Unit) {
    val modoTexto = when (modo) {
        ModoTema.CLARO -> stringResource(R.string.tema_modo_claro)
        ModoTema.OSCURO -> stringResource(R.string.tema_modo_oscuro)
        ModoTema.SISTEMA -> stringResource(R.string.tema_modo_sistema)
    }
    val descripcion = "${stringResource(R.string.tema_cambiar)} ($modoTexto)"
    IconButton(onClick = onClick, modifier = Modifier.size(44.dp)) {
        Text(
            text = when (modo) {
                ModoTema.CLARO -> "☀️"
                ModoTema.OSCURO -> "🌙"
                ModoTema.SISTEMA -> "🌗"
            },
            style = TiposOrderBot.iconoTema,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.semantics { contentDescription = descripcion },
        )
    }
}
