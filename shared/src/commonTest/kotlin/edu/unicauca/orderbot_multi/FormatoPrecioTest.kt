package edu.unicauca.orderbot_multi

import edu.unicauca.orderbot_multi.util.FormatoPrecio
import kotlin.test.Test
import kotlin.test.assertEquals

/** Equivalente a `FormatoPrecioTests.swift`. */
class FormatoPrecioTest {

    @Test
    fun formateaMilesConPunto() = assertEquals("$29.000", FormatoPrecio.aPrecio(29_000))

    @Test
    fun formateaMillones() = assertEquals("$1.234.567", FormatoPrecio.aPrecio(1_234_567))

    @Test
    fun formateaCero() = assertEquals("$0", FormatoPrecio.aPrecio(0))

    @Test
    fun formateaNumeroSinSeparador() = assertEquals("$500", FormatoPrecio.aPrecio(500))
}
