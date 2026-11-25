package edu.unicauca.orderbot_multi

import edu.unicauca.orderbot_multi.presentation.PedidoViewModel
import edu.unicauca.orderbot_multi.presentation.TemaViewModel

/**
 * Contenedor de dependencias compartido.
 *
 * Cada plataforma construye sus implementaciones concretas (driver SQLite
 * y almacén de preferencias) y expone los mismos ViewModels a su UI.
 */
expect class OrderBotContainer {
    val pedidoViewModel: PedidoViewModel
    val temaViewModel: TemaViewModel
}
