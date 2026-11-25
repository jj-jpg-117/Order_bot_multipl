import SwiftUI

/// Paleta de marca con los mismos roles que el `ColorScheme` de Material 3.
struct PaletaOrderBot {
    let primario: Color
    let sobrePrimario: Color
    let contenedorPrimario: Color
    let sobreContenedorPrimario: Color
    let secundario: Color
    let sobreSecundario: Color
    let terciario: Color
    let contenedorTerciario: Color
    let fondo: Color
    let sobreFondo: Color
    let superficie: Color
    let sobreSuperficie: Color
    let sobreSuperficieVariante: Color

    static let claro = PaletaOrderBot(
        primario: .azulOrderBot,
        sobrePrimario: .white,
        contenedorPrimario: .azulClaroOrderBot,
        sobreContenedorPrimario: .textoPrincipal,
        secundario: .textoSecundario,
        sobreSecundario: .white,
        terciario: .verdeOrderBot,
        contenedorTerciario: .verdeClaroOrderBot,
        fondo: .fondoOrderBot,
        sobreFondo: .textoPrincipal,
        superficie: .white,
        sobreSuperficie: .textoPrincipal,
        sobreSuperficieVariante: .textoSecundario
    )

    static let oscuro = PaletaOrderBot(
        primario: .azulOrderBotOscuro,
        sobrePrimario: .textoPrincipal,
        contenedorPrimario: .azulContenedorOscuro,
        sobreContenedorPrimario: .textoPrincipalOscuro,
        secundario: .textoSecundarioOscuro,
        sobreSecundario: .textoPrincipal,
        terciario: .verdeOrderBotOscuro,
        contenedorTerciario: .verdeContenedorOscuro,
        fondo: .fondoOscuro,
        sobreFondo: .textoPrincipalOscuro,
        superficie: .superficieOscura,
        sobreSuperficie: .textoPrincipalOscuro,
        sobreSuperficieVariante: .textoSecundarioOscuro
    )
}

private struct ClavePaletaOrderBot: EnvironmentKey {
    static let defaultValue = PaletaOrderBot.claro
}

extension EnvironmentValues {
    var paletaOrderBot: PaletaOrderBot {
        get { self[ClavePaletaOrderBot.self] }
        set { self[ClavePaletaOrderBot.self] = newValue }
    }
}

/// Equivalente a `OrderBotTheme { ... }` de Compose.
struct OrderBotTema: ViewModifier {
    let oscuro: Bool
    let esquemaPreferido: ColorScheme?

    func body(content: Content) -> some View {
        content
            .environment(\.paletaOrderBot, oscuro ? .oscuro : .claro)
            .preferredColorScheme(esquemaPreferido)
    }
}

extension View {
    func orderBotTema(oscuro: Bool, esquemaPreferido: ColorScheme? = nil) -> some View {
        modifier(OrderBotTema(oscuro: oscuro, esquemaPreferido: esquemaPreferido))
    }
}
