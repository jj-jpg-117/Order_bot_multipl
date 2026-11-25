import SwiftUI
import shared

/// Pedidos ya guardados en SQLite, leídos desde `commonMain`.
struct PantallaHistorial: View {

    @Environment(\.paletaOrderBot) private var paleta

    let pedidos: [PedidoGuardado]
    let onVolver: () -> Void
    let modoTema: ModoTema
    let onCambiarTema: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Spacer(minLength: 0)
                BotonModoTema(modo: modoTema, alPulsar: onCambiarTema)
            }

            Spacer().frame(height: 16)

            Text(L.t("historial_titulo"))
                .font(FuentesOrderBot.titulo)
                .foregroundStyle(paleta.sobreFondo)
                .frame(maxWidth: .infinity, alignment: .center)

            Spacer().frame(height: 16)

            if pedidos.isEmpty {
                Spacer(minLength: 0)
                Text(L.t("historial_vacio"))
                    .font(FuentesOrderBot.cuerpo)
                    .foregroundStyle(paleta.sobreSuperficieVariante)
                    .multilineTextAlignment(.center)
                Spacer(minLength: 0)
            } else {
                ScrollView {
                    LazyVStack(spacing: 12) {
                        ForEach(pedidos, id: \.numeroPedido) { pedido in
                            TarjetaPedidoGuardado(pedido: pedido)
                        }
                    }
                }
            }

            Spacer().frame(height: 12)

            BotonSecundario(texto: L.t("historial_boton_volver"), accion: onVolver)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding(24)
        .background(paleta.fondo.ignoresSafeArea())
    }
}

private struct TarjetaPedidoGuardado: View {

    @Environment(\.paletaOrderBot) private var paleta

    let pedido: PedidoGuardado

    private var cantidadTotal: Int32 {
        pedido.items.reduce(Int32(0)) { $0 + $1.cantidad }
    }

    private var resumenProductos: String {
        pedido.items
            .map { "\($0.cantidad)× \($0.producto.nombre)" }
            .joined(separator: ", ")
    }

    var body: some View {
        OrderBotCard {
            Text(L.t("historial_numero_pedido", pedido.numeroPedido))
                .font(.system(size: 20, weight: .bold))
                .foregroundStyle(paleta.sobreSuperficie)

            Spacer().frame(height: 4)

            Text(pedido.fechaTexto)
                .font(FuentesOrderBot.detalle)
                .foregroundStyle(paleta.sobreSuperficieVariante)

            Spacer().frame(height: 10)

            Text(L.t("historial_items", cantidadTotal))
                .font(FuentesOrderBot.cuerpoDestacado)
                .foregroundStyle(paleta.sobreSuperficie)

            Text(resumenProductos)
                .font(FuentesOrderBot.detalle)
                .foregroundStyle(paleta.sobreSuperficieVariante)

            Spacer().frame(height: 10)

            Text(L.t("historial_direccion", pedido.direccion))
                .font(FuentesOrderBot.detalle)
                .foregroundStyle(paleta.sobreSuperficieVariante)

            Spacer().frame(height: 4)

            Text(L.t("historial_pago", pedido.metodoPago.etiqueta))
                .font(FuentesOrderBot.detalle)
                .foregroundStyle(paleta.sobreSuperficieVariante)

            Spacer().frame(height: 10)

            FilaPrecio(nombre: L.t("resumen_subtotal"), precio: pedido.subtotal.aPrecio)
            FilaPrecio(nombre: L.t("resumen_envio"), precio: pedido.costoEnvio.aPrecio)
            FilaPrecio(
                nombre: L.t("historial_total"),
                precio: pedido.total.aPrecio,
                destacado: true
            )
        }
    }
}

#Preview {
    PantallaHistorial(
        pedidos: [],
        onVolver: {},
        modoTema: .sistema,
        onCambiarTema: {}
    )
    .orderBotTema(oscuro: false)
}
