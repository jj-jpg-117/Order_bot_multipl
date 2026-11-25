package edu.unicauca.orderbot_multi.android.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.unicauca.orderbot_multi.android.R
import edu.unicauca.orderbot_multi.android.theme.TiposOrderBot
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonSecundario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.android.ui.componentes.OrderBotCard
import edu.unicauca.orderbot_multi.model.ModoTema
import java.util.Calendar

/** Pantalla 7: créditos y equipo de desarrollo. */
@Composable
fun PantallaCreditos(
    onVolver: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    val anioActual = Calendar.getInstance().get(Calendar.YEAR)
    val descripcionLogo = stringResource(R.string.logo_descripcion)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CabeceraPantalla(modoTema = modoTema, onCambiarTema = onCambiarTema)

        Spacer(Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.logo_bot1),
            contentDescription = descripcionLogo,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(38.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp),
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.creditos_titulo),
            style = TiposOrderBot.tituloCompacto,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.creditos_version, "1.0.0"),
            style = TiposOrderBot.cuerpo,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.creditos_derechos, anioActual),
            style = TiposOrderBot.secundario,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(28.dp))

        OrderBotCard {
            Text(
                text = stringResource(R.string.creditos_equipo_titulo),
                style = TiposOrderBot.tituloSeccion,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.creditos_integrante_1),
                style = TiposOrderBot.cuerpo,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = stringResource(R.string.creditos_integrante_2),
                style = TiposOrderBot.cuerpo,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(Modifier.height(28.dp))

        BotonSecundario(texto = stringResource(R.string.creditos_boton_volver), onClick = onVolver)
    }
}
