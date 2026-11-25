package edu.unicauca.orderbot_multi.model

import edu.unicauca.orderbot_multi.util.Textos

/**
 * Métodos de pago aceptados por OrderBot.
 *
 * `rawValue` conserva el texto guardado por las versiones anteriores
 * ("EFECTIVO", "TARJETA", "BILLETERA_DIGITAL") para mantener la
 * compatibilidad de pedidos ya persistidos.
 */
enum class MetodoPago(val rawValue: String, val claveEtiqueta: String) {
    EFECTIVO("EFECTIVO", "pago_efectivo"),
    TARJETA("TARJETA", "pago_tarjeta"),
    BILLETERA_DIGITAL("BILLETERA_DIGITAL", "pago_billetera"),
    ;

    val etiqueta: String get() = Textos.t(claveEtiqueta)

    companion object {
        fun desdeRaw(valor: String?): MetodoPago =
            entries.firstOrNull { it.rawValue == valor } ?: EFECTIVO
    }
}
