import SwiftUI

/// Tarjeta del catálogo con un slot de acción al final.
struct TarjetaProducto<Accion: View>: View {

    @Environment(\.paletaOrderBot) private var paleta

    private let emoji: String
    private let nombre: String
    private let descripcion: String?
    private let precio: String?
    private let accion: Accion

    init(
        emoji: String,
        nombre: String,
        descripcion: String? = nil,
        precio: String? = nil,
        @ViewBuilder accion: () -> Accion
    ) {
        self.emoji = emoji
        self.nombre = nombre
        self.descripcion = descripcion
        self.precio = precio
        self.accion = accion()
    }

    var body: some View {
        OrderBotCard {
            HStack(alignment: .center, spacing: 14) {
                Text(emoji)
                    .font(FuentesOrderBot.emojiGrande)

                VStack(alignment: .leading, spacing: 0) {
                    Text(nombre)
                        .font(FuentesOrderBot.subtitulo)
                        .foregroundStyle(paleta.sobreSuperficie)

                    if let descripcion {
                        Text(descripcion)
                            .font(FuentesOrderBot.pie)
                            .foregroundStyle(paleta.sobreSuperficieVariante)
                    }

                    if let precio {
                        Text(precio)
                            .font(FuentesOrderBot.detalleDestacado)
                            .foregroundStyle(paleta.primario)
                            .padding(.top, 4)
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)

                if !(accion is EmptyView) {
                    accion
                        .padding(.leading, 12)
                }
            }
        }
    }
}

extension TarjetaProducto where Accion == EmptyView {
    init(emoji: String, nombre: String, descripcion: String? = nil, precio: String? = nil) {
        self.init(
            emoji: emoji,
            nombre: nombre,
            descripcion: descripcion,
            precio: precio,
            accion: { EmptyView() }
        )
    }
}
