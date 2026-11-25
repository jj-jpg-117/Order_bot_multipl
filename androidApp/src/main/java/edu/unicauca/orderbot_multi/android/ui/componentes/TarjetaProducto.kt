package edu.unicauca.orderbot_multi.android.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot

/**
 * Tarjeta del catálogo con un "slot" de acción al final.
 *
 * Equivale a `TarjetaProducto<Accion>.swift`: quien llama decide si pone
 * un botón "Elegir", el control de cantidad o nada.
 */
@Composable
fun TarjetaProducto(
    emoji: String,
    nombre: String,
    descripcion: String? = null,
    precio: String? = null,
    accion: @Composable RowScope.() -> Unit = {},
) {
    OrderBotCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(emoji, style = TiposOrderBot.emojiGrande)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    nombre,
                    style = TiposOrderBot.subtitulo,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                if (descripcion != null) {
                    Text(
                        descripcion,
                        style = TiposOrderBot.pie,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (precio != null) {
                    Text(
                        precio,
                        style = TiposOrderBot.detalleDestacado,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
            }
            accion()
        }
    }
}
