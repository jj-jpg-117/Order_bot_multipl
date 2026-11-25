package edu.unicauca.orderbot_multi.util

import platform.Foundation.NSBundle

/** Instala `Localizable.xcstrings` como fuente de textos en iOS. */
fun instalarProveedorTextosIOS() {
    instalarProveedorTextos { clave ->
        val traducido = NSBundle.mainBundle.localizedStringForKey(clave, null, null)
        if (traducido == clave) Textos.textoPorDefecto(clave) else traducido
    }
}
