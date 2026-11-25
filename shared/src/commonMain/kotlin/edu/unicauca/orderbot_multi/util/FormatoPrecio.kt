package edu.unicauca.orderbot_multi.util

import kotlin.math.abs

/**
 * Formateo de pesos colombianos using `.` como separador de miles.
 *
 * Se implementa a mano para que el resultado sea idéntico en JVM y en
 * Kotlin/Native (donde `java.text.NumberFormat` no está disponible).
 */
object FormatoPrecio {

    /** `29000` -> `$29.000`, `1234567` -> `$1.234.567`. */
    fun aPrecio(valor: Int): String {
        val signo = if (valor < 0) "-" else ""
        val digitos = abs(valor.toLong()).toString()
        val resultado = StringBuilder()
        digitos.forEachIndexed { indice, digito ->
            if (indice > 0 && (digitos.length - indice) % 3 == 0) {
                resultado.append('.')
            }
            resultado.append(digito)
        }
        return signo + "\$" + resultado.toString()
    }

    /** Variante para valores guardados como `Long`. */
    fun aPrecio(valor: Long): String = aPrecio(valor.toInt())
}
