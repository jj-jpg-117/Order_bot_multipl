package edu.unicauca.orderbot_multi.model

import edu.unicauca.orderbot_multi.util.Textos

/**
 * Producto del menú.
 *
 * Los textos son claves de localización (equivalente a `stringResource`)
 * y el precio siempre es un entero en pesos colombianos.
 */
data class Producto(
    val id: String,
    val claveNombre: String,
    val emoji: String,
    val precio: Int,
    val claveDescripcion: String? = null,
) {
    /** Nombre visible resuelto contra el catálogo de textos. */
    val nombre: String get() = Textos.t(claveNombre)

    /** Descripción opcional resuelta contra el catálogo de textos. */
    val descripcion: String? get() = claveDescripcion?.let { Textos.t(it) }
}

/** Catálogo: única fuente de verdad para productos y precios. */
object Catalogo {

    const val precioEnvio: Int = 5_000

    val hamburguesa = Producto(
        id = "hamburguesa",
        claveNombre = "producto_hamburguesa",
        emoji = "🍔",
        precio = 20_000,
        claveDescripcion = "producto_hamburguesa_desc",
    )
    val pizza = Producto(
        id = "pizza",
        claveNombre = "producto_pizza",
        emoji = "🍕",
        precio = 12_000,
        claveDescripcion = "producto_pizza_desc",
    )
    val tacos = Producto(
        id = "tacos",
        claveNombre = "producto_tacos",
        emoji = "🌮",
        precio = 15_000,
        claveDescripcion = "producto_tacos_desc",
    )
    val burrito = Producto(
        id = "burrito",
        claveNombre = "producto_burrito",
        emoji = "🌯",
        precio = 14_000,
        claveDescripcion = "producto_burrito_desc",
    )
    val pollo = Producto(
        id = "pollo",
        claveNombre = "producto_pollo",
        emoji = "🍗",
        precio = 18_000,
        claveDescripcion = "producto_pollo_desc",
    )
    val hotdog = Producto(
        id = "hotdog",
        claveNombre = "producto_hotdog",
        emoji = "🌭",
        precio = 8_000,
        claveDescripcion = "producto_hotdog_desc",
    )
    val ensalada = Producto(
        id = "ensalada",
        claveNombre = "producto_ensalada",
        emoji = "🥗",
        precio = 10_000,
        claveDescripcion = "producto_ensalada_desc",
    )
    val papas = Producto(
        id = "papas",
        claveNombre = "producto_papas",
        emoji = "🍟",
        precio = 5_000,
    )
    val bebida = Producto(
        id = "bebida",
        claveNombre = "producto_bebida",
        emoji = "🥤",
        precio = 4_000,
    )
    val helado = Producto(
        id = "helado",
        claveNombre = "producto_helado",
        emoji = "🍦",
        precio = 6_000,
        claveDescripcion = "producto_helado_desc",
    )

    /** Menú completo mostrado en la conversación. */
    val menu: List<Producto> = listOf(
        hamburguesa, pizza, tacos, burrito, pollo,
        hotdog, ensalada, papas, bebida, helado,
    )

    fun porId(id: String): Producto? = menu.firstOrNull { it.id == id }
}
