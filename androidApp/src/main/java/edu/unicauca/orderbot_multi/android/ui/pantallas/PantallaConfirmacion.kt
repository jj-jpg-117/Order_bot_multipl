package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonPrimario
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonSecundario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.android.ui.componentes.OrderBotCard
import edu.unicauca.orderbot_multi.model.ModoTema

/** Pantalla 6: confirmación del pedido ya guardado. */
@Composable
fun PantallaConfirmacion(
    numeroPedido: Int,
    onVolverInicio: () -> Unit,
    onVerHistorial: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CabeceraPantalla(modoTema = modoTema, onCambiarTema = onCambiarTema)

        Spacer(Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "✓",
                fontSize = 58.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.confirmacion_titulo),
            style = TiposOrderBot.titulo,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.confirmacion_mensaje),
            style = TiposOrderBot.cuerpo,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(24.dp))

        OrderBotCard {
            Text(
                text = stringResource(R.string.confirmacion_numero_pedido, numeroPedido),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.confirmacion_entrega_estimada),
                style = TiposOrderBot.detalle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.confirmacion_metodo_pago),
                style = TiposOrderBot.detalle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.confirmacion_estado),
                style = TiposOrderBot.detalle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Spacer(Modifier.height(24.dp))

        // Pendiente de conectar con el seguimiento en vivo (igual que en Swift).
        BotonPrimario(
            texto = stringResource(R.string.confirmacion_boton_seguir),
            onClick = {},
        )

        Spacer(Modifier.height(10.dp))

        BotonSecundario(
            texto = stringResource(R.string.confirmacion_boton_historial),
            onClick = onVerHistorial,
        )

        Spacer(Modifier.height(10.dp))

        BotonSecundario(
            texto = stringResource(R.string.confirmacion_boton_volver),
            onClick = onVolverInicio,
        )
    }
}
