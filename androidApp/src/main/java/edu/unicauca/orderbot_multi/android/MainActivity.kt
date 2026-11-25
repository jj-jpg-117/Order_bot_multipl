package edu.unicauca.orderbot_multi.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import edu.unicauca.orderbot_multi.OrderBotContainer
import edu.unicauca.orderbot_multi.android.theme.OrderBotTheme
import edu.unicauca.orderbot_multi.android.ui.NavegacionOrderBot

/** Único entrada de la app Android: crea el contenedor compartido y aplica el tema. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contenedor = OrderBotContainer(this)

        setContent {
            val modoTema by contenedor.temaViewModel.modoTema.collectAsState()

            OrderBotTheme(modoTema = modoTema) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavegacionOrderBot(container = contenedor)
                }
            }
        }
    }
}
