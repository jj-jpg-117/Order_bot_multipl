import SwiftUI

/// Tarjeta base con el estilo unificado de OrderBot.
struct OrderBotCard<Contenido: View>: View {

    @Environment(\.paletaOrderBot) private var paleta

    private let contenido: Contenido

    init(@ViewBuilder contenido: () -> Contenido) {
        self.contenido = contenido()
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            contenido
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(18)
        .background(
            paleta.superficie,
            in: RoundedRectangle(cornerRadius: 18, style: .continuous)
        )
        .shadow(color: .black.opacity(0.10), radius: 2, x: 0, y: 1)
    }
}
