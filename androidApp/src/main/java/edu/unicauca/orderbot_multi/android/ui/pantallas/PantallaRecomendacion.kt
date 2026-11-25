package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonPrimario
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonSecundario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.android.ui.componentes.OrderBotCard
import edu.unicauca.orderbot_multi.model.Catalogo
import edu.unicauca.orderbot_multi.model.ModoTema
import edu.unicauca.orderbot_multi.util.FormatoPrecio

/** Pantalla 3: ofrece papas y bebida como complementos. */
@Composable
fun PantallaRecomendacion(
    onAceptar: () -> Unit,
    onRechazar: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CabeceraPantalla(modoTema = modoTema, onCambiarTema = onCambiarTema)

        Spacer(Modifier.height(18.dp))

        Text(Catalogo.hamburguesa.emoji, style = TiposOrderBot.emojiMuyGrande)

        Spacer(Modifier.height(18.dp))

        Text(
            text = stringResource(R.string.recomendacion_titulo),
            style = TiposOrderBot.titulo,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.recomendacion_pregunta),
            style = TiposOrderBot.cuerpoGrande,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.recomendacion_detalle),
            style = TiposOrderBot.detalle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(28.dp))

        OrderBotCard {
            ComplementoItem(
                emoji = Catalogo.papas.emoji,
                nombre = Catalogo.papas.nombre,
                precio = FormatoPrecio.aPrecio(Catalogo.papas.precio),
            )
            Spacer(Modifier.height(8.dp))
            ComplementoItem(
                emoji = Catalogo.bebida.emoji,
                nombre = Catalogo.bebida.nombre,
                precio = FormatoPrecio.aPrecio(Catalogo.bebida.precio),
            )
        }

        Spacer(Modifier.height(24.dp))

        BotonPrimario(texto = stringResource(R.string.recomendacion_boton_aceptar), onClick = onAceptar)

        Spacer(Modifier.height(10.dp))

        BotonSecundario(texto = stringResource(R.string.recomendacion_boton_rechazar), onClick = onRechazar)
    }
}

@Composable
private fun ComplementoItem(emoji: String, nombre: String, precio: String) {
    Column {
        Text(
            text = "$emoji $nombre",
            style = TiposOrderBot.subtitulo,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = precio,
            style = TiposOrderBot.detalle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
