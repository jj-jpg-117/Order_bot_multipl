package edu.unicauca.orderbot_multi

import edu.unicauca.orderbot_multi.data.DatabaseDriverFactory
import edu.unicauca.orderbot_multi.data.PedidoRepository
import edu.unicauca.orderbot_multi.data.PreferenciasUsuario
import edu.unicauca.orderbot_multi.data.crearSettingsIOS
import edu.unicauca.orderbot_multi.presentation.PedidoViewModel
import edu.unicauca.orderbot_multi.presentation.TemaViewModel
import edu.unicauca.orderbot_multi.util.instalarProveedorTextosIOS

actual class OrderBotContainer {

    init {
        instalarProveedorTextosIOS()
    }

    private val repositorio = PedidoRepository(DatabaseDriverFactory())

    actual val pedidoViewModel: PedidoViewModel = PedidoViewModel(repositorio)

    actual val temaViewModel: TemaViewModel =
        TemaViewModel(PreferenciasUsuario(crearSettingsIOS()))
}
