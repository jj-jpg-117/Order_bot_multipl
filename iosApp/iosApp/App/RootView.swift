import SwiftUI
import shared

/// Raíz de la app: aplica el tema compartido y monta la navegación.
struct RootView: View {

    @Environment(\.colorScheme) private var esquemaSistema

    @ObservedObject var estadoPedido: EstadoPedido
    @ObservedObject var estadoTema: EstadoTema
    @ObservedObject var router: AppRouter

    /// Equivale a `isSystemInDarkTheme()` solo cuando el modo es SISTEMA.
    private var temaOscuro: Bool {
        let modo = estadoTema.modo
        if modo.esSistema { return esquemaSistema == .dark }
        if modo.esClaro { return false }
        return true
    }

    /// `nil` = seguir al sistema.
    private var esquemaPreferido: ColorScheme? {
        let modo = estadoTema.modo
        if modo.esSistema { return nil }
        return modo.esClaro ? .light : .dark
    }

    var body: some View {
        NavigationStack(path: $router.camino) {
            PantallaInicio(
                onComenzar: { router.irA(.conversacion) },
                onVerCreditos: { router.irA(.creditos) },
                onVerHistorial: { router.irA(.historial) },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )
            .navigationDestination(for: Ruta.self) { ruta in
                pantalla(ruta)
                    // Cada pantalla dibuja su propio encabezado.
                    .toolbar(.hidden, for: .navigationBar)
            }
        }
        .tint(temaOscuro ? .azulOrderBotOscuro : .azulOrderBot)
        .environment(\.paletaOrderBot, temaOscuro ? .oscuro : .claro)
        .preferredColorScheme(esquemaPreferido)
    }

    @ViewBuilder
    private func pantalla(_ ruta: Ruta) -> some View {
        switch ruta {
        case .creditos:
            PantallaCreditos(
                onVolver: { router.volver() },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )

        case .conversacion:
            PantallaConversacion(
                items: estadoPedido.items,
                onAgregar: { estadoPedido.agregar($0) },
                onQuitar: { estadoPedido.quitar($0) },
                onContinuar: { router.irA(.recomendacion) },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )

        case .recomendacion:
            PantallaRecomendacion(
                onAceptar: {
                    estadoPedido.aceptarRecomendacion()
                    router.irA(.entrega)
                },
                onRechazar: { router.irA(.entrega) },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )

        case .entrega:
            PantallaEntrega(
                direccion: estadoPedido.direccion,
                metodoPago: estadoPedido.metodoPago,
                onCambiarDireccion: { estadoPedido.setDireccion($0) },
                onCambiarMetodoPago: { estadoPedido.setMetodoPago($0) },
                onContinuar: { router.irA(.resumen) },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )

        case .resumen:
            PantallaResumen(
                resumen: estadoPedido.resumen,
                direccion: estadoPedido.direccion,
                metodoPago: estadoPedido.metodoPago,
                mensajeError: estadoPedido.mensajeError,
                guardando: estadoPedido.guardando,
                onConfirmar: confirmarPedido,
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )

        case .confirmacion(let numeroPedido):
            PantallaConfirmacion(
                numeroPedido: numeroPedido,
                onVolverInicio: { router.volverAlInicio() },
                onVerHistorial: { router.irA(.historial) },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )

        case .historial:
            PantallaHistorial(
                pedidos: estadoPedido.historial,
                onVolver: { router.volver() },
                modoTema: estadoTema.modo,
                onCambiarTema: cambiarTema
            )
            .onAppear { estadoPedido.cargarHistorial() }
        }
    }

    private func cambiarTema() {
        estadoTema.cambiarModoTema()
    }

    /// Guarda en SQLite compartido y muestra la confirmación.
    private func confirmarPedido() {
        if let numero = estadoPedido.confirmar() {
            router.mostrarConfirmacion(numero: numero)
        }
    }
}
