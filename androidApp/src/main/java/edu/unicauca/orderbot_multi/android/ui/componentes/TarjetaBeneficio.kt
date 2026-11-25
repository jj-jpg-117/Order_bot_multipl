package edu.unicauca.orderbot_multi.android.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot

/** Tarjeta de beneficio de la pantalla de inicio. Equivale a `TarjetaBeneficio.swift`. */
@Composable
fun TarjetaBeneficio(
    icono: String,
    titulo: String,
    descripcion: String,
) {
    OrderBotCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(icono, fontSize = 28.sp)
            Column {
                Text(
                    titulo,
                    style = TiposOrderBot.cuerpoDestacado,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    descripcion,
                    style = TiposOrderBot.secundario,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
