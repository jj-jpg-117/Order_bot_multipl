import shared

/// Pequeños adaptadores para que las vistas SwiftUI puedan tratar los
/// enums y valores del módulo `shared` como si fueran tipos nativos.

extension ModoTema {
    var esSistema: Bool { self === ModoTema.sistema }
    var esClaro: Bool { self === ModoTema.claro }
    var esOscuro: Bool { self === ModoTema.oscuro }
}

extension MetodoPago {
    var esEfectivo: Bool { self === MetodoPago.efectivo }
}

extension Int32 {
    /// Equivale a la extensión `Int.aPrecio` del proyecto Swift original.
    var aPrecio: String { FormatoPrecio.shared.aPrecio(valor: self) }
}
