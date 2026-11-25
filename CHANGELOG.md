# Changelog

Todos los cambios relevantes de OrderBot KMP se registran aquí. El formato sigue las ideas de [Keep a Changelog](https://keepachangelog.com/es-ES/1.1.0/).

## [1.0.0] - 2026-11-25

### Añadido

- Proyecto Kotlin Multiplatform con lógica compartida en `commonMain`.
- UI nativa para Android con Jetpack Compose y Navigation Compose.
- UI nativa para iOS con SwiftUI y `NavigationStack`.
- Modelos, repositorio y ViewModels compartidos (`PedidoViewModel`, `TemaViewModel`).
- Persistencia local con SQLDelight y transacciones para pedidos e ítems.
- Pantalla de historial con pedidos previamente guardados, importes históricos y fecha formateada.
- Acceso al historial desde Inicio y Confirmación en ambas plataformas.
- Tema claro, oscuro y automático persistido con Multiplatform Settings.
- Localización en español con claves compartidas entre Android e iOS.
- Icono de aplicación generado desde el logo de la pantalla de inicio.
- DTO `PedidoRemoto` preparado para una futura integración con Firestore.
- Documentación inicial: `README.md`, `CONTRIBUTING.md` y este changelog.

### Notas

- Firestore permanece desactivado.
- “Seguir pedido en vivo” conserva el comportamiento de la versión Swift original: todavía no tiene acción.
