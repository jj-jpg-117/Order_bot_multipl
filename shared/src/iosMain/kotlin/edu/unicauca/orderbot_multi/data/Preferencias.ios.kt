package edu.unicauca.orderbot_multi.data

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings

/** Crea la instancia de preferencias respaldada por `NSUserDefaults`. */
fun crearSettingsIOS(): Settings = NSUserDefaultsSettings.Factory().create(null)
