package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonPrimario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.model.MetodoPago
import edu.unicauca.orderbot_multi.model.ModoTema

/** Pantalla 4: dirección y método de pago. El botón se habilita solo con dirección. */
@Composable
fun PantallaEntrega(
    direccion: String,
    metodoPago: MetodoPago,
    onCambiarDireccion: (String) -> Unit,
    onCambiarMetodoPago: (MetodoPago) -> Unit,
    onContinuar: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        CabeceraPantalla(
            titulo = stringResource(R.string.entrega_titulo),
            modoTema = modoTema,
            onCambiarTema = onCambiarTema,
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.entrega_direccion_titulo),
            style = TiposOrderBot.cuerpoDestacado,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = direccion,
            onValueChange = onCambiarDireccion,
            placeholder = { Text(stringResource(R.string.entrega_direccion_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done,
            ),
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.entrega_metodo_pago_titulo),
            style = TiposOrderBot.cuerpoDestacado,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(8.dp))

        MetodoPago.entries.forEach { metodo ->
            FilaMetodoPago(
                metodo = metodo,
                seleccionado = metodo == metodoPago,
                onSeleccionar = { onCambiarMetodoPago(metodo) },
            )
        }

        Spacer(Modifier.weight(1f))

        BotonPrimario(
            texto = stringResource(R.string.entrega_boton_continuar),
            habilitado = direccion.isNotBlank(),
            onClick = onContinuar,
        )
    }
}

@Composable
private fun FilaMetodoPago(
    metodo: MetodoPago,
    seleccionado: Boolean,
    onSeleccionar: () -> Unit,
) {
    val forma = RoundedCornerShape(12.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .border(
                width = 1.dp,
                color = if (seleccionado) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                },
                shape = forma,
            )
            .clickable(onClick = onSeleccionar)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = seleccionado, onClick = onSeleccionar)
        Text(
            text = metodo.etiqueta,
            style = TiposOrderBot.cuerpo,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
