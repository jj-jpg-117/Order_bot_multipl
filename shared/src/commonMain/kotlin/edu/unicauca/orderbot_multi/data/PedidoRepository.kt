package edu.unicauca.orderbot_multi.data

import edu.unicauca.orderbot_multi.db.OrderBotDatabase
import edu.unicauca.orderbot_multi.model.Catalogo
import edu.unicauca.orderbot_multi.model.ItemPedido
import edu.unicauca.orderbot_multi.model.MetodoPago
import edu.unicauca.orderbot_multi.model.PedidoGuardado
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

    /** Pedidos guardados, de más reciente a más antiguo, con sus ítems. */
    fun listarPedidos(): List<PedidoGuardado> =
        queries.listarPedidos().executeAsList().map { fila ->
            val items = queries.itemsDePedido(fila.numeroPedido).executeAsList().mapNotNull { item ->
                Catalogo.porId(item.productoId)?.let { producto ->
                    ItemPedido(producto = producto, cantidad = item.cantidad.toInt())
                }
            }
            PedidoGuardado(
                numeroPedido = fila.numeroPedido.toInt(),
                direccion = fila.direccion,
                metodoPago = MetodoPago.desdeRaw(fila.metodoPago),
                subtotal = fila.subtotal.toInt(),
                costoEnvio = fila.costoEnvio.toInt(),
                total = fila.total.toInt(),
                fechaMillis = fila.fechaMillis,
                items = items,
            )
        }
}
