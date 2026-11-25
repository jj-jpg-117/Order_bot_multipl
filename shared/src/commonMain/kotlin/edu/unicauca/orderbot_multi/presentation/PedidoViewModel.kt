package edu.unicauca.orderbot_multi.presentation

import edu.unicauca.orderbot_multi.data.PedidoRepository
import edu.unicauca.orderbot_multi.model.Catalogo
import edu.unicauca.orderbot_multi.model.ItemPedido
import edu.unicauca.orderbot_multi.model.MetodoPago
import edu.unicauca.orderbot_multi.model.Producto
import edu.unicauca.orderbot_multi.model.ResumenPedido
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Estado del pedido compartido por todas las pantallas. */
class PedidoViewModel(private val repositorio: PedidoRepository) : ViewModelObservable() {

    private val _items = MutableStateFlow<List<ItemPedido>>(emptyList())
    val items: StateFlow<List<ItemPedido>> = _items.asStateFlow()

    private val _direccion = MutableStateFlow("")
    val direccion: StateFlow<String> = _direccion.asStateFlow()

    private val _metodoPago = MutableStateFlow(MetodoPago.EFECTIVO)
    val metodoPago: StateFlow<MetodoPago> = _metodoPago.asStateFlow()

    private val _guardando = MutableStateFlow(false)
    val guardando: StateFlow<Boolean> = _guardando.asStateFlow()

    private val _mensajeError = MutableStateFlow<String?>(null)
    val mensajeError: StateFlow<String?> = _mensajeError.asStateFlow()

    val resumen: ResumenPedido get() = ResumenPedido(_items.value)

    // Getters con tipo concreto para el puente Swift/Objective-C.
    fun itemsActuales(): List<ItemPedido> = _items.value
    fun direccionActual(): String = _direccion.value
    fun metodoPagoActual(): MetodoPago = _metodoPago.value
    fun guardandoActual(): Boolean = _guardando.value
    fun mensajeErrorActual(): String? = _mensajeError.value

    /** Suma una unidad del producto (o lo agrega con cantidad 1). */
    fun agregar(producto: Producto) {
        val actuales = _items.value.toMutableList()
        val indice = actuales.indexOfFirst { it.producto.id == producto.id }
        if (indice >= 0) {
            actuales[indice] = actuales[indice].conCantidad(actuales[indice].cantidad + 1)
        } else {
            actuales.add(ItemPedido(producto = producto, cantidad = 1))
        }
        _items.value = actuales
        notificarCambios()
    }

    /** Resta una unidad; si llega a cero, saca el producto del carrito. */
    fun quitar(producto: Producto) {
        val actuales = _items.value.toMutableList()
        val indice = actuales.indexOfFirst { it.producto.id == producto.id }
        if (indice < 0) return
        val renglon = actuales[indice]
        if (renglon.cantidad > 1) {
            actuales[indice] = renglon.conCantidad(renglon.cantidad - 1)
        } else {
            actuales.removeAt(indice)
        }
        _items.value = actuales
        notificarCambios()
    }

    fun setDireccion(valor: String) {
        _direccion.value = valor
        notificarCambios()
    }

    fun setMetodoPago(metodo: MetodoPago) {
        _metodoPago.value = metodo
        notificarCambios()
    }

    /** Agrega papas y bebida, los complementos ofrecidos por el bot. */
    fun aceptarRecomendacion() {
        agregar(Catalogo.papas)
        agregar(Catalogo.bebida)
    }

    /** Guarda el pedido, limpia el carrito y devuelve su número. */
    fun confirmar(): Int? {
        _guardando.value = true
        notificarCambios()
        return try {
            val numero = repositorio.guardarPedido(resumen, _direccion.value, _metodoPago.value)
            _items.value = emptyList()
            _direccion.value = ""
            _metodoPago.value = MetodoPago.EFECTIVO
            _mensajeError.value = null
            numero
        } catch (error: Exception) {
            _mensajeError.value = "No se pudo guardar el pedido: ${error.message ?: "error desconocido"}"
            null
        } finally {
            _guardando.value = false
            notificarCambios()
        }
    }
}
