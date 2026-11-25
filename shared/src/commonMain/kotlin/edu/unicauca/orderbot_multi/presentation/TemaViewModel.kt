package edu.unicauca.orderbot_multi.presentation

import edu.unicauca.orderbot_multi.data.PreferenciasUsuario
import edu.unicauca.orderbot_multi.model.ModoTema
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Modo de tema actual, persistido entre ejecuciones. */
class TemaViewModel(private val preferencias: PreferenciasUsuario) : ViewModelObservable() {

    private val _modoTema = MutableStateFlow(preferencias.modoTema)
    val modoTema: StateFlow<ModoTema> = _modoTema.asStateFlow()

    fun modoTemaActual(): ModoTema = _modoTema.value

    /** Alterna SISTEMA -> CLARO -> OSCURO -> SISTEMA y lo guarda. */
    fun cambiarModoTema() {
        val siguiente = _modoTema.value.siguiente()
        _modoTema.value = siguiente
        preferencias.modoTema = siguiente
        notificarCambios()
    }
}
