package edu.unicauca.orderbot_multi.model

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Pedido ya persistido en SQLite, con sus ítems reconstructidos.
 *
 * Es el modelo de solo lectura que alimenta la pantalla de historial en
 * Android e iOS. Se crea desde las filas de `OrderBotDatabase`, por lo que
 * conserva los importes y la dirección tal como se guardaron (no se
 * recalculan con el catálogo actual).
 */
data class PedidoGuardado(
    val numeroPedido: Int,
    val direccion: String,
    val metodoPago: MetodoPago,
    val subtotal: Int,
    val costoEnvio: Int,
    val total: Int,
    val fechaMillis: Long,
    val items: List<ItemPedido>,
) {
    /** Resumen equivalente al del carrito, pero con el envío histórico. */
    val resumen: ResumenPedido
        get() = ResumenPedido(items, precioEnvio = costoEnvio)

    /** Fecha local en formato `dd/MM/yyyy HH:mm`, igual en ambas apps. */
    val fechaTexto: String
        get() {
            val fecha = Instant.fromEpochMilliseconds(fechaMillis)
                .toLocalDateTime(TimeZone.currentSystemDefault())
            val dia = fecha.dayOfMonth.toString().padStart(2, '0')
            val mes = fecha.monthNumber.toString().padStart(2, '0')
            val hora = fecha.hour.toString().padStart(2, '0')
            val minuto = fecha.minute.toString().padStart(2, '0')
            return "$dia/$mes/${fecha.year} $hora:$minuto"
        }
}
