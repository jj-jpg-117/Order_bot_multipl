import Foundation
import shared

/// Puente entre los ViewModels de Kotlin y SwiftUI.
///
/// Kotlin expone `observarCambios(listener:)`; este `ObservableObject`
/// vuelve a leer los getters concretos (`itemsActuales()`, etc.) en cada
/// cambio y publica los valores, de modo que las vistas se redibujan
/// igual que lo hacen con `collectAsState()` en Compose.
final class EstadoPedido: ObservableObject {

    @Published private(set) var items: [ItemPedido] = []
    @Published private(set) var direccion: String = ""
    @Published private(set) var metodoPago: MetodoPago = .efectivo
    @Published private(set) var resumen: ResumenPedido
    @Published private(set) var mensajeError: String?
    @Published private(set) var guardando: Bool = false
    @Published private(set) var historial: [PedidoGuardado] = []

    let viewModel: PedidoViewModel
    private var suscripcion: Suscripcion?

    init(_ viewModel: PedidoViewModel) {
        self.viewModel = viewModel
        self.resumen = viewModel.resumen
        self.items = viewModel.itemsActuales()
        self.direccion = viewModel.direccionActual()
        self.metodoPago = viewModel.metodoPagoActual()
        self.mensajeError = viewModel.mensajeErrorActual()
        self.guardando = viewModel.guardandoActual()
        self.historial = viewModel.historialActual()

        self.suscripcion = viewModel.observarCambios { [weak self] in
            guard let self else { return }
            if Thread.isMainThread {
                self.sincronizar()
            } else {
                DispatchQueue.main.async { self.sincronizar() }
            }
        }
    }

    deinit {
        suscripcion?.cancelar()
    }

    private func sincronizar() {
        items = viewModel.itemsActuales()
        direccion = viewModel.direccionActual()
        metodoPago = viewModel.metodoPagoActual()
        resumen = viewModel.resumen
        mensajeError = viewModel.mensajeErrorActual()
        guardando = viewModel.guardandoActual()
        historial = viewModel.historialActual()
    }

    func agregar(_ producto: Producto) { viewModel.agregar(producto: producto) }
    func quitar(_ producto: Producto) { viewModel.quitar(producto: producto) }
    func setDireccion(_ valor: String) { viewModel.setDireccion(valor: valor) }
    func setMetodoPago(_ metodo: MetodoPago) { viewModel.setMetodoPago(metodo: metodo) }
    func aceptarRecomendacion() { viewModel.aceptarRecomendacion() }
    func cargarHistorial() { viewModel.cargarHistorial() }

    /// `confirmar()` devuelve un `KotlinInt`, que hereda de `NSNumber`.
    func confirmar() -> Int? {
        guard let numero = viewModel.confirmar() else { return nil }
        return numero.intValue
    }
}

/// Puente para el modo de tema persistido en `commonMain`.
final class EstadoTema: ObservableObject {

    @Published private(set) var modo: ModoTema

    private let viewModel: TemaViewModel
    private var suscripcion: Suscripcion?

    init(_ viewModel: TemaViewModel) {
        self.viewModel = viewModel
        self.modo = viewModel.modoTemaActual()

        self.suscripcion = viewModel.observarCambios { [weak self] in
            guard let self else { return }
            let nuevo = viewModel.modoTemaActual()
            if Thread.isMainThread {
                self.modo = nuevo
            } else {
                DispatchQueue.main.async { self.modo = nuevo }
            }
        }
    }

    deinit {
        suscripcion?.cancelar()
    }

    func cambiarModoTema() {
        viewModel.cambiarModoTema()
    }
}
