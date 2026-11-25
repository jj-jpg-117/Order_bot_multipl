package edu.unicauca.orderbot_multi.data

import com.russhwolf.settings.Settings
import edu.unicauca.orderbot_multi.model.ModoTema

/** Persistencia del modo de tema (DataStore en Android, UserDefaults en iOS). */
class PreferenciasUsuario(private val settings: Settings) {

    var modoTema: ModoTema
        get() = ModoTema.desdeRaw(settings.getStringOrNull(CLAVE_MODO_TEMA))
        set(value) = settings.putString(CLAVE_MODO_TEMA, value.rawValue)

    companion object {
        const val CLAVE_MODO_TEMA: String = "modo_tema"
    }
}
