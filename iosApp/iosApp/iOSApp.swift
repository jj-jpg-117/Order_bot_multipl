import SwiftUI
import shared

@main
struct iOSApp: App {

    private let estadoPedido: EstadoPedido
    private let estadoTema: EstadoTema
    private let router: AppRouter

    init() {
        // El contenedor compartido crea el driver SQLite y el almacén de
        // preferencias concretos de iOS una sola vez, igual que en Android.
        let contenedor = OrderBotContainer()
        estadoPedido = EstadoPedido(contenedor.pedidoViewModel)
        estadoTema = EstadoTema(contenedor.temaViewModel)
        router = AppRouter()
    }

    var body: some Scene {
        WindowGroup {
            RootView(
                estadoPedido: estadoPedido,
                estadoTema: estadoTema,
                router: router
            )
        }
    }
}
