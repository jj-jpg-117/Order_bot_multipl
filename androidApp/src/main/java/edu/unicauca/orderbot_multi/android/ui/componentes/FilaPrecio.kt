package edu.unicauca.orderbot_multi.android.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot

/** Fila "concepto → precio" usada en el resumen. */
@Composable
fun FilaPrecio(
    nombre: String,
    precio: String,
    destacado: Boolean = false,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            nombre,
            style = if (destacado) TiposOrderBot.cuerpoGrandeDestacado else TiposOrderBot.detalle,
            color = if (destacado) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        )
        Text(
            precio,
            style = if (destacado) TiposOrderBot.cuerpoGrandeDestacado else TiposOrderBot.detalleDestacado,
            color = if (destacado) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
        )
    }
}
