package edu.unicauca.orderbot_multi.data

import edu.unicauca.orderbot_multi.db.OrderBotDatabase
import edu.unicauca.orderbot_multi.model.MetodoPago
import edu.unicauca.orderbot_multi.model.ResumenPedido
import kotlinx.datetime.Clock

/**
 * Puerta de acceso única a la base de datos local.
 *
 * Equivale a `PedidoRepository` + `PedidoDao` de Room. El guardado es
 * transaccional: o se guardan el pedido y todos sus ítems, o no se guarda
 * nada.
 */
class PedidoRepository(private val driverFactory: DatabaseDriverFactory) {

    private val database: OrderBotDatabase = OrderBotDatabase(driverFactory.createDriver())
    private val queries = database.orderBotDatabaseQueries

    /** Persiste el pedido y devuelve su número visible. */
    fun guardarPedido(
        resumen: ResumenPedido,
        direccion: String,
        metodoPago: MetodoPago,
    ): Int {
        val numero = queries.ultimoNumero().executeAsOne().toInt() + 1

        database.transaction {
            queries.insertarPedido(
                numeroPedido = numero.toLong(),
                direccion = direccion,
                metodoPago = metodoPago.rawValue,
                subtotal = resumen.subtotal.toLong(),
                costoEnvio = resumen.precioEnvio.toLong(),
                total = resumen.total.toLong(),
                fechaMillis = Clock.System.now().toEpochMilliseconds(),
            )
            resumen.items.forEach { item ->
                queries.insertarItem(
                    productoId = item.producto.id,
                    cantidad = item.cantidad.toLong(),
                    pedidoNumero = numero.toLong(),
                )
            }
        }

        return numero
    }
}
