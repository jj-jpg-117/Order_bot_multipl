package edu.unicauca.orderbot_multi

import android.content.Context
import edu.unicauca.orderbot_multi.data.DatabaseDriverFactory
import edu.unicauca.orderbot_multi.data.PedidoRepository
import edu.unicauca.orderbot_multi.data.PreferenciasUsuario
import edu.unicauca.orderbot_multi.data.crearSettingsAndroid
import edu.unicauca.orderbot_multi.presentation.PedidoViewModel
import edu.unicauca.orderbot_multi.presentation.TemaViewModel
import edu.unicauca.orderbot_multi.util.instalarProveedorTextosAndroid

actual class OrderBotContainer(context: Context) {

    init {
        instalarProveedorTextosAndroid(context)
    }

    private val repositorio = PedidoRepository(DatabaseDriverFactory(context))

    actual val pedidoViewModel: PedidoViewModel = PedidoViewModel(repositorio)

    actual val temaViewModel: TemaViewModel =
        TemaViewModel(PreferenciasUsuario(crearSettingsAndroid(context)))
}
