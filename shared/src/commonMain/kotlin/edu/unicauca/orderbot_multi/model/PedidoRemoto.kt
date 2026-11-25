package edu.unicauca.orderbot_multi.model

import kotlinx.serialization.Serializable

/**
 * Modelo preparado para Firestore (colección `pedidos`).
 *
 * Firestore está desactivado en esta primera fase, pero se conserva el DTO
 * con valores por defecto para decodificar documentos incompletos.
 */
@Serializable
data class PedidoRemoto(
    var id: String = "",
    var direccion: String = "",
    var metodoPago: String = "",
    var total: Int = 0,
    var fechaMillis: Long = 0L,
) {
    constructor() : this("", "", "", 0, 0L)

    constructor(direccion: String, metodoPago: String, total: Int) : this(
        id = "",
        direccion = direccion,
        metodoPago = metodoPago,
        total = total,
        fechaMillis = 0L,
    )
}
