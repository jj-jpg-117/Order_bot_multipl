package edu.unicauca.orderbot_multi.data

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

/** Crea la instancia de preferencias respaldada por `SharedPreferences`. */
fun crearSettingsAndroid(context: Context): Settings =
    SharedPreferencesSettings.Factory(context.applicationContext).create(null)
