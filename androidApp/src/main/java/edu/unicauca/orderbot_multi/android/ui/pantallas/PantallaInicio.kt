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
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonPrimario
import edu.unicauca.orderbot_multi.android.ui.componentes.BotonSecundario
import edu.unicauca.orderbot_multi.android.ui.componentes.CabeceraPantalla
import edu.unicauca.orderbot_multi.android.ui.componentes.TarjetaBeneficio
import edu.unicauca.orderbot_multi.model.ModoTema

/** Pantalla 1: bienvenida y beneficios. Equivale a `PantallaInicio.swift`. */
@Composable
fun PantallaInicio(
    onComenzar: () -> Unit,
    onVerCreditos: () -> Unit,
    modoTema: ModoTema,
    onCambiarTema: () -> Unit,
) {
    val descripcionLogo = stringResource(R.string.logo_descripcion)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CabeceraPantalla(modoTema = modoTema, onCambiarTema = onCambiarTema)

        Spacer(Modifier.height(24.dp))

        Image(
            painter = painterResource(R.drawable.logo_bot1),
            contentDescription = descripcionLogo,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(44.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(18.dp),
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.app_name),
            style = TiposOrderBot.nombreApp,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.inicio_eslogan),
            style = TiposOrderBot.eslogan,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(32.dp))

        TarjetaBeneficio(
            icono = "⚡",
            titulo = stringResource(R.string.inicio_beneficio_rapido_titulo),
            descripcion = stringResource(R.string.inicio_beneficio_rapido_desc),
        )

        Spacer(Modifier.height(12.dp))

        TarjetaBeneficio(
            icono = "✨",
            titulo = stringResource(R.string.inicio_beneficio_sugerencias_titulo),
            descripcion = stringResource(R.string.inicio_beneficio_sugerencias_desc),
        )

        Spacer(Modifier.height(32.dp))

        BotonPrimario(texto = stringResource(R.string.inicio_boton_comenzar), onClick = onComenzar)

        Spacer(Modifier.height(10.dp))

        BotonSecundario(texto = stringResource(R.string.inicio_boton_creditos), onClick = onVerCreditos)
    }
}
