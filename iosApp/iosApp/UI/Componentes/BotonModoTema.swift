import SwiftUI
import shared

/// Alterna el modo de tema compartido: ☀️ claro, 🌙 oscuro, 🌗 automático.
struct BotonModoTema: View {

    @Environment(\.paletaOrderBot) private var paleta

    let modo: ModoTema
    let alPulsar: () -> Void

    var body: some View {
        Button(action: alPulsar) {
            Text(emoji)
                .font(FuentesOrderBot.iconoTema)
                .foregroundStyle(paleta.sobreSuperficie)
                .frame(minWidth: 44, minHeight: 44)
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
        .accessibilityLabel(descripcionAccesible)
    }

    /// Los enums de Kotlin son clases en Swift: se compara por identidad.
    private var emoji: String {
        if modo.esClaro { return "☀️" }
        if modo.esOscuro { return "🌙" }
        return "🌗"
    }

    private var descripcionAccesible: String {
        let claveModo = modo.esClaro
            ? "tema_modo_claro"
            : (modo.esOscuro ? "tema_modo_oscuro" : "tema_modo_sistema")
        return "\(L.t("tema_cambiar")) (\(L.t(claveModo)))"
    }
}
