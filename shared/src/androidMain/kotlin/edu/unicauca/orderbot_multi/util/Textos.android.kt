package edu.unicauca.orderbot_multi.util

import android.content.Context

/** Instala `strings.xml` como fuente de textos en Android. */
fun instalarProveedorTextosAndroid(context: Context) {
    val aplicacion = context.applicationContext
    instalarProveedorTextos { clave ->
        val id = aplicacion.resources.getIdentifier(clave, "string", aplicacion.packageName)
        if (id == 0) Textos.textoPorDefecto(clave) else aplicacion.getString(id)
    }
}
