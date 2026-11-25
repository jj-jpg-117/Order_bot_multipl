package edu.unicauca.orderbot_multi.android.ui.componentes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.orderbot_multi.android.R

/** Selector de cantidad: [−] n [+]. */
@Composable
fun ControlCantidad(
    cantidad: Int,
    onAgregar: () -> Unit,
    onQuitar: () -> Unit,
) {
    val quitar = stringResource(R.string.cantidad_quitar)
    val agregar = stringResource(R.string.cantidad_agregar)
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = onQuitar,
            modifier = Modifier.size(40.dp).semantics { contentDescription = quitar },
        ) {
            Text("−", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
        }
        Text(
            text = cantidad.toString(),
            modifier = Modifier.width(20.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
        )
        IconButton(
            onClick = onAgregar,
            modifier = Modifier.size(40.dp).semantics { contentDescription = agregar },
        ) {
            Text("+", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}
