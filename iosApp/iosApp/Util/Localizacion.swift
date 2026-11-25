import Foundation

/// Acceso a los textos localizados con las mismas claves que Android.
///
/// Las claves viven en `Resources/Localizable.xcstrings`, copiado del
/// proyecto Swift original. El proveedor instalado por `OrderBotContainer`
/// lee exactamente este mismo bundle.
enum L {

    static func t(_ clave: String) -> String {
        Bundle.main.localizedString(forKey: clave, value: clave, table: nil)
    }

    /// Aplica argumentos a `%1$@` / `%1$d`, igual que `stringResource` en Compose.
    static func t(_ clave: String, _ argumentos: CVarArg...) -> String {
        String(format: t(clave), arguments: argumentos)
    }
}
