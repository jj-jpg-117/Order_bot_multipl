package edu.unicauca.orderbot_multi.model

/** Renglón del pedido: un producto y cuántas unidades se pidieron. */
data class ItemPedido(
    val producto: Producto,
    val cantidad: Int,
) {
    init {
        require(cantidad > 0) { "La cantidad debe ser positiva, era $cantidad" }
    }

    /** Precio unitario × cantidad. */
    val total: Int get() = producto.precio * cantidad

    /** Copia del renglón con otra cantidad. */
    fun conCantidad(nuevaCantidad: Int): ItemPedido = copy(cantidad = nuevaCantidad)
}

/** Lógica de negocio del pedido, separada de la UI y de la base de datos. */
data class ResumenPedido(
    val items: List<ItemPedido>,
    val precioEnvio: Int = Catalogo.precioEnvio,
) {
    /** Suma de (precio × cantidad) de todos los ítems. */
    val subtotal: Int get() = items.fold(0) { total, item -> total + item.total }

    /** Subtotal más el costo de envío. */
    val total: Int get() = subtotal + precioEnvio

    fun estaVacio(): Boolean = items.isEmpty()
}
