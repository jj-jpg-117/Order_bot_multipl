package edu.unicauca.orderbot_multi

import edu.unicauca.orderbot_multi.model.Catalogo
import edu.unicauca.orderbot_multi.model.ItemPedido
import edu.unicauca.orderbot_multi.model.ResumenPedido
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Equivalente a `ResumenPedidoTests.swift`. */
class ResumenPedidoTest {

    @Test
    fun itemCalculaSuTotal() {
        val item = ItemPedido(producto = Catalogo.hamburguesa, cantidad = 3)
        assertEquals(60_000, item.total)
    }

    @Test
    fun subtotalSumaTodosLosItems() {
        val resumen = ResumenPedido(
            items = listOf(
                ItemPedido(producto = Catalogo.hamburguesa, cantidad = 2),
                ItemPedido(producto = Catalogo.papas, cantidad = 1),
            ),
        )
        assertEquals(45_000, resumen.subtotal)
    }

    @Test
    fun totalIncluyeElEnvioPorDefecto() {
        val resumen = ResumenPedido(items = listOf(ItemPedido(producto = Catalogo.pizza, cantidad = 1)))
        assertEquals(Catalogo.precioEnvio, resumen.precioEnvio)
        assertEquals(17_000, resumen.total)
    }

    @Test
    fun totalRespetaUnEnvioPersonalizado() {
        val resumen = ResumenPedido(
            items = listOf(ItemPedido(producto = Catalogo.bebida, cantidad = 1)),
            precioEnvio = 0,
        )
        assertEquals(4_000, resumen.total)
    }

    @Test
    fun resumenVacio() {
        val resumen = ResumenPedido(items = emptyList())
        assertTrue(resumen.estaVacio())
        assertEquals(0, resumen.subtotal)
        assertEquals(Catalogo.precioEnvio, resumen.total)
    }
}
