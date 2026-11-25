package edu.unicauca.orderbot_multi.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.unicauca.orderbot_multi.OrderBotContainer
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaConfirmacion
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaConversacion
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaCreditos
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaEntrega
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaInicio
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaRecomendacion
import edu.unicauca.orderbot_multi.android.ui.pantallas.PantallaResumen
import edu.unicauca.orderbot_multi.model.ResumenPedido

/** Rutas del flujo, equivalentes al `NavigationStack` de SwiftUI. */
object Rutas {
    const val INICIO = "inicio"
    const val CONVERSACION = "conversacion"
    const val RECOMENDACION = "recomendacion"
    const val ENTREGA = "entrega"
    const val RESUMEN = "resumen"
    const val CONFIRMACION = "confirmacion/{numeroPedido}"
    const val CREDITOS = "creditos"

    fun confirmacion(numeroPedido: Int): String = "confirmacion/$numeroPedido"
}

/**
 * Grafo de navegación compartido.
 *
 * Los ViewModels viven en el contenedor (fuera del grafo), por lo que el
 * carrito sobrevive a los cambios de pantalla y se limpia al confirmar.
 */
@Composable
fun NavegacionOrderBot(container: OrderBotContainer) {
    val navController = rememberNavController()
    val pedidoViewModel = container.pedidoViewModel
    val temaViewModel = container.temaViewModel

    val items by pedidoViewModel.items.collectAsState()
    val direccion by pedidoViewModel.direccion.collectAsState()
    val metodoPago by pedidoViewModel.metodoPago.collectAsState()
    val mensajeError by pedidoViewModel.mensajeError.collectAsState()
    val guardando by pedidoViewModel.guardando.collectAsState()
    val modoTema by temaViewModel.modoTema.collectAsState()

    // El resumen se deriva del estado observado, nunca de StateFlow.value.
    val resumen = ResumenPedido(items)

    val cambiarTema = { temaViewModel.cambiarModoTema() }

    NavHost(navController = navController, startDestination = Rutas.INICIO) {
        composable(Rutas.INICIO) {
            PantallaInicio(
                onComenzar = { navController.navigate(Rutas.CONVERSACION) },
                onVerCreditos = { navController.navigate(Rutas.CREDITOS) },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }

        composable(Rutas.CONVERSACION) {
            PantallaConversacion(
                items = items,
                onAgregar = pedidoViewModel::agregar,
                onQuitar = pedidoViewModel::quitar,
                onContinuar = { navController.navigate(Rutas.RECOMENDACION) },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }

        composable(Rutas.RECOMENDACION) {
            PantallaRecomendacion(
                onAceptar = {
                    pedidoViewModel.aceptarRecomendacion()
                    navController.navigate(Rutas.ENTREGA)
                },
                onRechazar = { navController.navigate(Rutas.ENTREGA) },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }

        composable(Rutas.ENTREGA) {
            PantallaEntrega(
                direccion = direccion,
                metodoPago = metodoPago,
                onCambiarDireccion = pedidoViewModel::setDireccion,
                onCambiarMetodoPago = pedidoViewModel::setMetodoPago,
                onContinuar = { navController.navigate(Rutas.RESUMEN) },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }

        composable(Rutas.RESUMEN) {
            PantallaResumen(
                resumen = resumen,
                direccion = direccion,
                metodoPago = metodoPago,
                mensajeError = mensajeError,
                guardando = guardando,
                onConfirmar = {
                    val numero = pedidoViewModel.confirmar()
                    if (numero != null) {
                        navController.navigate(Rutas.confirmacion(numero)) {
                            // El pedido ya terminó: el back no debe volver al carrito.
                            popUpTo(Rutas.INICIO) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }

        composable(
            route = Rutas.CONFIRMACION,
            arguments = listOf(navArgument("numeroPedido") { type = NavType.IntType }),
        ) { entrada ->
            val numeroPedido = entrada.arguments?.getInt("numeroPedido") ?: 0
            PantallaConfirmacion(
                numeroPedido = numeroPedido,
                onVolverInicio = {
                    navController.navigate(Rutas.INICIO) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }

        composable(Rutas.CREDITOS) {
            PantallaCreditos(
                onVolver = { navController.popBackStack() },
                modoTema = modoTema,
                onCambiarTema = cambiarTema,
            )
        }
    }
}
