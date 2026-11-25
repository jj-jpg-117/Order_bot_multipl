package edu.unicauca.orderbot_multi.android.ui.componentes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.model.ModoTema

/** Encabezado común: título a la izquierda y selector de tema a la derecha. */
@Composable
fun CabeceraPantalla(
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
    titulo: String? = null,
    compacto: Boolean = false,
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        if (titulo != null) {
            Text(
                text = titulo,
                style = if (compacto) TiposOrderBot.tituloCompacto else TiposOrderBot.titulo,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f),
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
        BotonModoTema(modo = modoTema, onClick = onCambiarTema)
    }
}
