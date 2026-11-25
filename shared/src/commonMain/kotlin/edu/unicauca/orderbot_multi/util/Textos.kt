package edu.unicauca.orderbot_multi.util

/**
 * Acceso a los textos localizados.
 *
 * Las claves son exactamente las mismas del proyecto Swift
 * (`Localizable.xcstrings`) y del `strings.xml` de Android. El catálogo
 * por defecto vive en [ClavesTexto] para que `commonMain` funcione sin
 * contexto de plataforma; cada app puede instalar su proveedor real.
 */
object Textos {

    private var proveedor: (String) -> String = { clave -> textoPorDefecto(clave) }

    /** Instala el proveedor de la plataforma (Android resources / iOS Bundle). */
    fun instalarProveedor(proveedor: (String) -> String) {
        this.proveedor = proveedor
    }

    /** Texto del catálogo interno, sin pasar por el proveedor instalado. */
    fun textoPorDefecto(clave: String): String = ClavesTexto.es[clave] ?: clave

    fun t(clave: String): String = proveedor(clave)

    /**
     * Igual que `t` pero aplica argumentos a los marcadores.
     * Se aceptan los tres formatos usados históricamente: `%1$@`, `%1$s` y `%1$d`.
     */
    fun t(clave: String, vararg argumentos: Any): String {
        var resultado = t(clave)
        argumentos.forEachIndexed { indice, argumento ->
            val posicion = indice + 1
            val texto = argumento.toString()
            resultado = resultado
                .replace("%$posicion\$@", texto)
                .replace("%$posicion\$s", texto)
                .replace("%$posicion\$d", texto)
        }
        return resultado
    }
}

/** Instala el proveedor de textos desde cualquier plataforma. */
fun instalarProveedorTextos(proveedor: (String) -> String) =
    Textos.instalarProveedor(proveedor)

/** Catálogo esporádico, espejo de `Localizable.xcstrings`. */
object ClavesTexto {
    val es: Map<String, String> = mapOf(
        "%@ %@" to "%1\$@ %2\$@",
        "app_name" to "OrderBot",
        "cantidad_agregar" to "Agregar una unidad",
        "cantidad_quitar" to "Quitar una unidad",
        "confirmacion_boton_seguir" to "Seguir pedido en vivo",
        "confirmacion_boton_volver" to "Volver al inicio",
        "confirmacion_entrega_estimada" to "⏱️ Entrega estimada: 30 - 40 minutos",
        "confirmacion_estado" to "📦 Estado: Pedido recibido",
        "confirmacion_mensaje" to "Tu pedido ha sido recibido correctamente.",
        "confirmacion_metodo_pago" to "💳 Método de pago: Por definir",
        "confirmacion_numero_pedido" to "Pedido #%1\$d",
        "confirmacion_titulo" to "¡Pedido confirmado!",
        "conversacion_boton_continuar" to "Continuar",
        "conversacion_pregunta" to "¿Qué deseas pedir hoy?",
        "conversacion_saludo" to "Hola 👋",
        "conversacion_subtitulo" to "Asistente de pedidos",
        "conversacion_titulo" to "OrderBot 💬",
        "creditos_anio" to "%1\$d",
        "creditos_boton_volver" to "Volver",
        "creditos_derechos" to "© %1\$d OrderBot. Todos los derechos reservados.",
        "creditos_equipo_titulo" to "Equipo de desarrollo",
        "creditos_integrante_1" to "Juan Ruales",
        "creditos_integrante_2" to "Juan Perafan",
        "creditos_titulo" to "Acerca de OrderBot",
        "creditos_version" to "Versión %1\$@",
        "entrega_boton_continuar" to "Ver resumen",
        "entrega_direccion_placeholder" to "Ej: Calle 123 #45-67, Apto 801",
        "entrega_direccion_titulo" to "📍 Dirección de entrega",
        "entrega_metodo_pago_titulo" to "💳 Método de pago",
        "entrega_titulo" to "Datos de entrega",
        "inicio_beneficio_rapido_desc" to "Disponible las 24 horas.",
        "inicio_beneficio_rapido_titulo" to "Atención instantánea",
        "inicio_beneficio_sugerencias_desc" to "Encuentra opciones según tu pedido.",
        "inicio_beneficio_sugerencias_titulo" to "Sugerencias personalizadas",
        "inicio_boton_comenzar" to "Comenzar",
        "inicio_boton_creditos" to "Créditos",
        "inicio_eslogan" to "¡Pide fácil, recibe rápido!",
        "logo_descripcion" to "Logo de OrderBot",
        "pago_billetera" to "📱 Billetera digital",
        "pago_efectivo" to "💵 Efectivo",
        "pago_tarjeta" to "💳 Tarjeta",
        "producto_bebida" to "Bebida",
        "producto_boton_elegir" to "Elegir",
        "producto_burrito" to "Burrito",
        "producto_burrito_desc" to "Relleno de carne y frijoles",
        "producto_ensalada" to "Ensalada",
        "producto_ensalada_desc" to "Fresca y saludable",
        "producto_hamburguesa" to "Hamburguesa",
        "producto_hamburguesa_desc" to "Hamburguesa clásica",
        "producto_helado" to "Helado",
        "producto_helado_desc" to "Vainilla y chocolate",
        "producto_hotdog" to "Hot dog",
        "producto_hotdog_desc" to "Salchicha americana",
        "producto_papas" to "Papas",
        "producto_pizza" to "Pizza",
        "producto_pizza_desc" to "Porción familiar",
        "producto_pollo" to "Pollo broaster",
        "producto_pollo_desc" to "Crujiente y dorado",
        "producto_tacos" to "Tacos",
        "producto_tacos_desc" to "Tres unidades mexicanas",
        "recomendacion_boton_aceptar" to "Sí, claro",
        "recomendacion_boton_rechazar" to "No, gracias",
        "recomendacion_detalle" to "Agrega papas y bebida por un precio adicional.",
        "recomendacion_pregunta" to "¿Quieres completar tu pedido?",
        "recomendacion_titulo" to "¡Excelente elección!",
        "remota_titulo" to "Pedidos en la nube",
        "remota_vacia" to "No hay pedidos remotos.",
        "resumen_boton_confirmar" to "Confirmar Pedido",
        "resumen_direccion" to "Dirección: %1\$@",
        "resumen_envio" to "Envío",
        "resumen_pago" to "Pago: %1\$@",
        "resumen_seccion_entrega" to "🚚 Entrega",
        "resumen_seccion_productos" to "Productos",
        "resumen_subtotal" to "Subtotal",
        "resumen_titulo" to "Resumen del pedido",
        "resumen_total" to "TOTAL",
        "tema_cambiar" to "Cambiar tema",
        "tema_modo_claro" to "claro",
        "tema_modo_oscuro" to "oscuro",
        "tema_modo_sistema" to "automático (sistema)",
        "📍 %@ — Total: %@" to "📍 %1\$@ — Total: %2\$@",
    )
}
