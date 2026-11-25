package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonSecundario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.android.ui.componentes.FilaPrecio
import edu.unicauca.orderbot_multi.android.ui.componentes.OrderBotCard
import edu.unicauca.orderbot_multi.model.ModoTema
import edu.unicauca.orderbot_multi.model.PedidoGuardado
import edu.unicauca.orderbot_multi.util.FormatoPrecio

/** Pedidos ya guardados en SQLite, leídos desde `commonMain`. */
@Composable
fun PantallaHistorial(
    pedidos: List<PedidoGuardado>,
    onVolver: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CabeceraPantalla(modoTema = modoTema, onCambiarTema = onCambiarTema)

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.historial_titulo),
            style = TiposOrderBot.titulo,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(16.dp))

        if (pedidos.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.historial_vacio),
                    style = TiposOrderBot.cuerpo,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = pedidos, key = { it.numeroPedido }) { pedido ->
                    TarjetaPedidoGuardado(pedido = pedido)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        BotonSecundario(
            texto = stringResource(R.string.historial_boton_volver),
            onClick = onVolver,
        )
    }
}

@Composable
private fun TarjetaPedidoGuardado(pedido: PedidoGuardado) {
    OrderBotCard {
        Text(
            text = stringResource(R.string.historial_numero_pedido, pedido.numeroPedido),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = pedido.fechaTexto,
            style = TiposOrderBot.detalle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(
                R.string.historial_items,
                pedido.items.sumOf { it.cantidad },
            ),
            style = TiposOrderBot.cuerpoDestacado,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = pedido.items.joinToString(", ") { "${it.cantidad}× ${it.producto.nombre}" },
            style = TiposOrderBot.detalle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.historial_direccion, pedido.direccion),
            style = TiposOrderBot.detalle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.historial_pago, pedido.metodoPago.etiqueta),
            style = TiposOrderBot.detalle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(10.dp))

        FilaPrecio(
            nombre = stringResource(R.string.resumen_subtotal),
            precio = FormatoPrecio.aPrecio(pedido.subtotal),
        )
        FilaPrecio(
            nombre = stringResource(R.string.resumen_envio),
            precio = FormatoPrecio.aPrecio(pedido.costoEnvio),
        )
        FilaPrecio(
            nombre = stringResource(R.string.historial_total),
            precio = FormatoPrecio.aPrecio(pedido.total),
            destacado = true,
        )
    }
}
