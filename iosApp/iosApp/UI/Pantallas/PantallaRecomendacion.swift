import SwiftUI
import shared

/// Pantalla 3: ofrece agregar papas y bebida al pedido.
struct PantallaRecomendacion: View {

    @Environment(\.paletaOrderBot) private var paleta

    let onAceptar: () -> Void
    let onRechazar: () -> Void
    let modoTema: ModoTema
    let onCambiarTema: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Spacer(minLength: 0)
                BotonModoTema(modo: modoTema, alPulsar: onCambiarTema)
            }

            Text(Catalogo.shared.hamburguesa.emoji)
                .font(FuentesOrderBot.emojiMuyGrande)

            Spacer().frame(height: 18)

            Text(L.t("recomendacion_titulo"))
                .font(FuentesOrderBot.titulo)
                .foregroundStyle(paleta.sobreFondo)
                .multilineTextAlignment(.center)

            Spacer().frame(height: 12)

            Text(L.t("recomendacion_pregunta"))
                .font(FuentesOrderBot.cuerpoGrande)
                .foregroundStyle(paleta.sobreFondo)
                .multilineTextAlignment(.center)

            Spacer().frame(height: 8)

            Text(L.t("recomendacion_detalle"))
                .font(FuentesOrderBot.detalle)
                .foregroundStyle(paleta.sobreSuperficieVariante)
                .multilineTextAlignment(.center)

            Spacer().frame(height: 28)

            OrderBotCard {
                ComplementoItem(
                    emoji: Catalogo.shared.papas.emoji,
                    nombre: Catalogo.shared.papas.nombre,
                    precio: Catalogo.shared.papas.precio
                )
                Spacer().frame(height: 8)
                ComplementoItem(
                    emoji: Catalogo.shared.bebida.emoji,
                    nombre: Catalogo.shared.bebida.nombre,
                    precio: Catalogo.shared.bebida.precio
                )
            }

            Spacer().frame(height: 24)

            BotonPrimario(texto: L.t("recomendacion_boton_aceptar"), accion: onAceptar)

            Spacer().frame(height: 10)

            BotonSecundario(texto: L.t("recomendacion_boton_rechazar"), accion: onRechazar)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
        .padding(24)
        .background(paleta.fondo.ignoresSafeArea())
    }
}

/// Nombre + precio de un complemento dentro de la tarjeta.
private struct ComplementoItem: View {

    @Environment(\.paletaOrderBot) private var paleta

    let emoji: String
    let nombre: String
    let precio: Int32

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("\(emoji) \(nombre)")
                .font(FuentesOrderBot.subtitulo)
                .foregroundStyle(paleta.sobreSuperficie)
            Text(precio.aPrecio)
                .foregroundStyle(paleta.sobreSuperficieVariante)
        }
    }
}

#Preview {
    PantallaRecomendacion(
        onAceptar: {},
        onRechazar: {},
        modoTema: .sistema,
        onCambiarTema: {}
    )
    .orderBotTema(oscuro: false)
}
