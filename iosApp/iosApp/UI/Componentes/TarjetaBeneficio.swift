import SwiftUI

/// Tarjeta de beneficio de la pantalla de inicio.
struct TarjetaBeneficio: View {

    @Environment(\.paletaOrderBot) private var paleta

    let icono: String
    let titulo: String
    let descripcion: String

    var body: some View {
        OrderBotCard {
            HStack(alignment: .center, spacing: 14) {
                Text(icono)
                    .font(.system(size: 28))

                VStack(alignment: .leading, spacing: 0) {
                    Text(titulo)
                        .font(FuentesOrderBot.cuerpoDestacado)
                        .foregroundStyle(paleta.sobreSuperficie)
                    Text(descripcion)
                        .font(FuentesOrderBot.secundario)
                        .foregroundStyle(paleta.sobreSuperficieVariante)
                }
            }
        }
    }
}
