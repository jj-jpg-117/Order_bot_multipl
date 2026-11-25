import SwiftUI
import shared

/// Pantalla 5: detalle del pedido antes de confirmar.
struct PantallaResumen: View {

    @Environment(\.paletaOrderBot) private var paleta

    let resumen: ResumenPedido
    let direccion: String
    let metodoPago: MetodoPago
    let mensajeError: String?
    let guardando: Bool
    let onConfirmar: () -> Void
    let modoTema: ModoTema
    let onCambiarTema: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Text(L.t("resumen_titulo"))
                    .font(FuentesOrderBot.titulo)
                    .foregroundStyle(paleta.sobreFondo)
                Spacer(minLength: 0)
                BotonModoTema(modo: modoTema, alPulsar: onCambiarTema)
            }

            Spacer().frame(height: 20)

            ScrollView {
                VStack(spacing: 16) {
                    tarjetaProductos
                    tarjetaEntrega
                }
            }
            .scrollIndicators(.hidden)

            Spacer().frame(height: 20)

            if let mensajeError {
                Text(mensajeError)
                    .font(FuentesOrderBot.secundario)
                    .foregroundStyle(.red)
                Spacer().frame(height: 8)
            }

            BotonPrimario(
                texto: L.t("resumen_boton_confirmar"),
                habilitado: !guardando,
                accion: onConfirmar
            )
        }
        .padding(20)
        .background(paleta.fondo.ignoresSafeArea())
    }

    private var tarjetaProductos: some View {
        OrderBotCard {
            Text(L.t("resumen_seccion_productos"))
                .font(FuentesOrderBot.tituloSeccion)
                .foregroundStyle(paleta.sobreSuperficie)

            Spacer().frame(height: 14)

            ForEach(resumen.items, id: \.producto.id) { item in
                let sufijo = item.cantidad > 1 ? " ×\(item.cantidad)" : ""
                FilaPrecio(
                    nombre: "\(item.producto.emoji) \(item.producto.nombre)\(sufijo)",
                    precio: item.total.aPrecio
                )
            }

            Divider()
                .padding(.vertical, 12)

            FilaPrecio(
                nombre: L.t("resumen_subtotal"),
                precio: resumen.subtotal.aPrecio
            )
            FilaPrecio(
                nombre: L.t("resumen_envio"),
                precio: resumen.precioEnvio.aPrecio
            )

            Divider()
                .padding(.vertical, 8)

            FilaPrecio(
                nombre: L.t("resumen_total"),
                precio: resumen.total.aPrecio,
                destacado: true
            )
        }
    }

    private var tarjetaEntrega: some View {
        OrderBotCard {
            Text(L.t("resumen_seccion_entrega"))
                .font(FuentesOrderBot.tituloSeccion)
                .foregroundStyle(paleta.sobreSuperficie)

            Spacer().frame(height: 8)

            Text(L.t("resumen_direccion", direccion))
                .foregroundStyle(paleta.sobreSuperficieVariante)

            Text(L.t("resumen_pago", metodoPago.etiqueta))
                .foregroundStyle(paleta.sobreSuperficieVariante)
        }
    }
}

#Preview {
    PantallaResumen(
        resumen: ResumenPedido(
            items: [
                ItemPedido(producto: Catalogo.shared.hamburguesa, cantidad: 1),
                ItemPedido(producto: Catalogo.shared.papas, cantidad: 1),
                ItemPedido(producto: Catalogo.shared.bebida, cantidad: 1),
            ],
            precioEnvio: 5000
        ),
        direccion: "Calle 123 #45-67",
        metodoPago: .efectivo,
        mensajeError: nil,
        guardando: false,
        onConfirmar: {},
        modoTema: .sistema,
        onCambiarTema: {}
    )
    .orderBotTema(oscuro: false)
}
