package edu.unicauca.orderbot_multi.model

/** Opciones de tema, con el mismo `rawValue` que la preferencia anterior. */
enum class ModoTema(val rawValue: String) {
    SISTEMA("SISTEMA"),
    CLARO("CLARO"),
    OSCURO("OSCURO"),
    ;

    /** Ciclo del botón: SISTEMA -> CLARO -> OSCURO -> SISTEMA. */
    fun siguiente(): ModoTema = when (this) {
        SISTEMA -> CLARO
        CLARO -> OSCURO
        OSCURO -> SISTEMA
    }

    companion object {
        fun desdeRaw(valor: String?): ModoTema =
            entries.firstOrNull { it.rawValue == valor } ?: SISTEMA
    }
}
