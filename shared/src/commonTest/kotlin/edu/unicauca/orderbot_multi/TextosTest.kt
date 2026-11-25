package edu.unicauca.orderbot_multi

import edu.unicauca.orderbot_multi.model.Catalogo
import edu.unicauca.orderbot_multi.model.MetodoPago
import edu.unicauca.orderbot_multi.util.Textos
import kotlin.test.Test
import kotlin.test.assertEquals

/** Comprueba que las claves y los marcadores llegan igual que en Swift. */
class TextosTest {

    @Test
    fun claveSinArgumentos() {
        assertEquals("OrderBot", Textos.t("app_name"))
    }

    @Test
    fun claveConNumero() {
        assertEquals("Pedido #1024", Textos.t("confirmacion_numero_pedido", 1024))
    }

    @Test
    fun claveConTexto() {
        assertEquals("Dirección: Calle 1", Textos.t("resumen_direccion", "Calle 1"))
    }

    @Test
    fun productosYMetodosResuelvenSuEtiqueta() {
        assertEquals("Hamburguesa", Catalogo.hamburguesa.nombre)
        assertEquals("💵 Efectivo", MetodoPago.EFECTIVO.etiqueta)
    }
}
