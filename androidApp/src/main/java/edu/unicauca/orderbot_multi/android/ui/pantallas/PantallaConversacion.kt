package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items as lazyItems
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import edu.unicauca.orderbot_multi.android.ui.componentes.ControlCantidad
import edu.unicauca.orderbot_multi.android.ui.componentes.OrderBotCard
import edu.unicauca.orderbot_multi.android.ui.componentes.TarjetaProducto
import edu.unicauca.orderbot_multi.model.Catalogo
import edu.unicauca.orderbot_multi.model.ItemPedido
import edu.unicauca.orderbot_multi.model.ModoTema
import edu.unicauca.orderbot_multi.model.Producto
import edu.unicauca.orderbot_multi.util.FormatoPrecio

/** Pantalla 2: saludo del bot y catálogo con controles de cantidad. */
@Composable
fun PantallaConversacion(
    items: List<ItemPedido>,
    onAgregar: (Producto) -> Unit,
    onQuitar: (Producto) -> Unit,
    onContinuar: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        CabeceraPantalla(
            titulo = stringResource(R.string.conversacion_titulo),
            compacto = true,
            modoTema = modoTema,
            onCambiarTema = onCambiarTema,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.conversacion_subtitulo),
            style = TiposOrderBot.secundario,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp))

        OrderBotCard {
            Text(
                text = stringResource(R.string.conversacion_saludo),
                style = TiposOrderBot.cuerpoGrandeDestacado,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.conversacion_pregunta),
                style = TiposOrderBot.cuerpo,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            lazyItems(Catalogo.menu, key = { it.id }) { producto ->
                val cantidad = items.firstOrNull { it.producto.id == producto.id }?.cantidad ?: 0
                TarjetaProducto(
                    emoji = producto.emoji,
                    nombre = producto.nombre,
                    descripcion = producto.descripcion,
                    precio = FormatoPrecio.aPrecio(producto.precio),
                ) {
                    if (cantidad == 0) {
                        Button(
                            onClick = { onAgregar(producto) },
                            modifier = Modifier.height(34.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                        ) {
                            Text(
                                text = stringResource(R.string.producto_boton_elegir),
                                style = TiposOrderBot.secundario,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    } else {
                        ControlCantidad(
                            cantidad = cantidad,
                            onAgregar = { onAgregar(producto) },
                            onQuitar = { onQuitar(producto) },
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        BotonPrimario(
            texto = stringResource(R.string.conversacion_boton_continuar),
            habilitado = items.isNotEmpty(),
            onClick = onContinuar,
        )
    }
}
