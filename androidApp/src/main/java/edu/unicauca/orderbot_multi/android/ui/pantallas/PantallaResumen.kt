package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonPrimario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.android.ui.componentes.FilaPrecio
import edu.unicauca.orderbot_multi.android.ui.componentes.OrderBotCard
import edu.unicauca.orderbot_multi.model.MetodoPago
import edu.unicauca.orderbot_multi.model.ModoTema
import edu.unicauca.orderbot_multi.model.ResumenPedido
import edu.unicauca.orderbot_multi.util.FormatoPrecio

/** Pantalla 5: detalle del pedido antes de confirmarlo. */
@Composable
fun PantallaResumen(
    resumen: ResumenPedido,
    direccion: String,
    metodoPago: MetodoPago,
    mensajeError: String?,
    guardando: Boolean,
    onConfirmar: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        CabeceraPantalla(
            titulo = stringResource(R.string.resumen_titulo),
            modoTema = modoTema,
            onCambiarTema = onCambiarTema,
        )

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OrderBotCard {
                Text(
                    text = stringResource(R.string.resumen_seccion_productos),
                    style = TiposOrderBot.tituloSeccion,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(14.dp))

                resumen.items.forEach { item ->
                    val sufijo = if (item.cantidad > 1) " ×${item.cantidad}" else ""
                    FilaPrecio(
                        nombre = "${item.producto.emoji} ${item.producto.nombre}$sufijo",
                        precio = FormatoPrecio.aPrecio(item.total),
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                FilaPrecio(
                    nombre = stringResource(R.string.resumen_subtotal),
                    precio = FormatoPrecio.aPrecio(resumen.subtotal),
                )
                FilaPrecio(
                    nombre = stringResource(R.string.resumen_envio),
                    precio = FormatoPrecio.aPrecio(resumen.precioEnvio),
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                FilaPrecio(
                    nombre = stringResource(R.string.resumen_total),
                    precio = FormatoPrecio.aPrecio(resumen.total),
                    destacado = true,
                )
            }

            OrderBotCard {
                Text(
                    text = stringResource(R.string.resumen_seccion_entrega),
                    style = TiposOrderBot.tituloSeccion,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.resumen_direccion, direccion),
                    style = TiposOrderBot.detalle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = stringResource(R.string.resumen_pago, metodoPago.etiqueta),
                    style = TiposOrderBot.detalle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        if (mensajeError != null) {
            Text(
                text = mensajeError,
                style = TiposOrderBot.secundario,
                color = MaterialTheme.colorScheme.error,
            )
            Spacer(Modifier.height(8.dp))
        }

        BotonPrimario(
            texto = stringResource(R.string.resumen_boton_confirmar),
            habilitado = !guardando,
            onClick = onConfirmar,
        )
    }
}
