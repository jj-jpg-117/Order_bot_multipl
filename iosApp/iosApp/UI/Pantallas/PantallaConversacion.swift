import SwiftUI
import shared

/// Pantalla 2: el bot saluda y muestra el catálogo compartido.
struct PantallaConversacion: View {

    @Environment(\.paletaOrderBot) private var paleta

    let items: [ItemPedido]
    let onAgregar: (Producto) -> Void
    let onQuitar: (Producto) -> Void
    let onContinuar: () -> Void
    let modoTema: ModoTema
    let onCambiarTema: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Text(L.t("conversacion_titulo"))
                    .font(FuentesOrderBot.tituloCompacto)
                    .foregroundStyle(paleta.sobreFondo)
                Spacer(minLength: 0)
                BotonModoTema(modo: modoTema, alPulsar: onCambiarTema)
            }

            Text(L.t("conversacion_subtitulo"))
                .font(FuentesOrderBot.secundario)
                .foregroundStyle(paleta.sobreSuperficieVariante)
                .frame(maxWidth: .infinity, alignment: .leading)

            Spacer().frame(height: 16)

            OrderBotCard {
                Text(L.t("conversacion_saludo"))
                    .font(FuentesOrderBot.cuerpoGrandeDestacado)
                    .foregroundStyle(paleta.sobreSuperficie)
                Spacer().frame(height: 6)
                Text(L.t("conversacion_pregunta"))
                    .font(FuentesOrderBot.cuerpo)
                    .foregroundStyle(paleta.sobreSuperficie)
            }

            Spacer().frame(height: 16)

            ScrollView {
                LazyVStack(spacing: 14) {
                    ForEach(Catalogo.shared.menu, id: \.id) { producto in
                        filaProducto(producto)
                    }
                }
            }
            .scrollIndicators(.hidden)

            Spacer().frame(height: 16)

            BotonPrimario(
                texto: L.t("conversacion_boton_continuar"),
                habilitado: !items.isEmpty,
                accion: onContinuar
            )
        }
        .padding(20)
        .background(paleta.fondo.ignoresSafeArea())
    }

    @ViewBuilder
    private func filaProducto(_ producto: Producto) -> some View {
        let cantidad = items.first { $0.producto.id == producto.id }?.cantidad ?? 0

        TarjetaProducto(
            emoji: producto.emoji,
            nombre: producto.nombre,
            descripcion: producto.descripcion,
            precio: producto.precio.aPrecio
        ) {
            if cantidad == 0 {
                Button {
                    onAgregar(producto)
                } label: {
                    Text(L.t("producto_boton_elegir"))
                        .font(.system(size: 14, weight: .medium))
                        .foregroundStyle(paleta.sobrePrimario)
                        .padding(.horizontal, 14)
                        .padding(.vertical, 8)
                        .background(
                            paleta.primario,
                            in: RoundedRectangle(cornerRadius: 12, style: .continuous)
                        )
                }
                .buttonStyle(.plain)
            } else {
                ControlCantidad(
                    cantidad: Int(cantidad),
                    onAgregar: { onAgregar(producto) },
                    onQuitar: { onQuitar(producto) }
                )
            }
        }
    }
}

#Preview {
    PantallaConversacion(
        items: [ItemPedido(producto: Catalogo.shared.hamburguesa, cantidad: 2)],
        onAgregar: { _ in },
        onQuitar: { _ in },
        onContinuar: {},
        modoTema: .sistema,
        onCambiarTema: {}
    )
    .orderBotTema(oscuro: false)
}
