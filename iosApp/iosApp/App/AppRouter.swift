import Foundation

/// Rutas type-safe, equivalentes a `Rutas.kt` de Compose.
enum Ruta: Hashable {
    case conversacion
    case recomendacion
    case entrega
    case resumen
    case confirmacion(numeroPedido: Int)
    case creditos
    case historial
}

/// Pila de navegación, equivalente al `NavHostController`.
///
/// Se usa `ObservableObject` (en vez de `@Observable`) para mantener
/// compatibilidad con el despliegue mínimo de iOS 16 del proyecto.
final class AppRouter: ObservableObject {
    @Published var camino: [Ruta] = []

    func irA(_ ruta: Ruta) {
        camino.append(ruta)
    }

    /// Equivale a `popBackStack()`.
    func volver() {
        guard !camino.isEmpty else { return }
        camino.removeLast()
    }

    /// `navigate(Confirmacion) { popUpTo(Inicio) }`.
    func mostrarConfirmacion(numero: Int) {
        camino = [.confirmacion(numeroPedido: numero)]
    }

    /// Reinicia el flujo dejando solo la pantalla inicial.
    func volverAlInicio() {
        camino.removeAll()
    }
}
