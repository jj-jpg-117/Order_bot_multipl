package edu.unicauca.orderbot_multi.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/** Suscripción que la UI debe cancelar cuando su ciclo de vida termina. */
interface Suscripcion {
    fun cancelar()
}

/**
 * Base de los ViewModels compartidos.
 *
 * Android consume los `StateFlow` con `collectAsState`, mientras que
 * SwiftUI usa `observarCambios` (un callback simple compatible con
 * Objective-C) y vuelve a leer los getters `...Actual()`.
 */
abstract class ViewModelObservable {

    private val cambios = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    /** Registra un listener; se invoca una vez de inmediato y después en cada cambio. */
    fun observarCambios(listener: () -> Unit): Suscripcion {
        listener()
        val alcance = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        val trabajo = alcance.launch {
            cambios.collect { listener() }
        }
        return object : Suscripcion {
            override fun cancelar() {
                trabajo.cancel()
            }
        }
    }

    protected fun notificarCambios() {
        cambios.tryEmit(Unit)
    }
}
