# OrderBot KMP

Versión multiplataforma de **OrderBot**: la lógica de negocio, la persistencia y los ViewModels viven en `commonMain`, mientras que Android e iOS mantienen interfaces nativas (Jetpack Compose y SwiftUI).

El proyecto conserva las claves de localización, los formatos y el flujo de navegación de la aplicación Swift original.

## Características

- Catálogo de productos y carrito con cantidades.
- Flujo completo: inicio → conversación → recomendación → entrega → resumen → confirmación.
- **Historial de pedidos** con persistencia real en SQLite mediante SQLDelight.
- Tema claro, oscuro y automático guardado en preferencias compartidas.
- Textos localizados en español con las mismas claves en Android e iOS.
- Icono de aplicación generado a partir del logo usado en la pantalla de inicio.
- DTO `PedidoRemoto` conservado para una integración futura con Firestore (actualmente desactivado).

## Arquitectura

```text
OrderBot_multi/
├── shared/                     # commonMain + drivers por plataforma
│   ├── sqldelight/.../OrderBotDatabase.sq
│   └── kotlin/.../orderbot_multi/
│       ├── data/               # PedidoRepository, preferencias, driver SQLite
│       ├── model/              # Producto, ResumenPedido, PedidoGuardado, etc.
│       ├── presentation/       # PedidoViewModel, TemaViewModel
│       ├── util/               # FormatoPrecio, Textos
│       └── OrderBotContainer.kt
├── androidApp/                 # UI nativa con Jetpack Compose
│   └── src/main/java/.../android/
│       ├── ui/NavegacionOrderBot.kt
│       ├── ui/pantallas/
│       └── ui/componentes/
└── iosApp/                     # UI nativa con SwiftUI
    └── iosApp/
        ├── App/                # RootView, AppRouter, EstadoCompartido
        ├── UI/Pantallas/
        ├── UI/Componentes/
        └── Resources/Localizable.xcstrings
```

### Capa compartida

- `PedidoViewModel` expone el carrito, dirección, método de pago, estado de guardado e historial.
- `PedidoRepository` centraliza el acceso a SQLite:
  - `guardarPedido(...)` inserta el pedido y sus ítems en una transacción.
  - `listarPedidos()` reconstruye los pedidos guardados de más reciente a más antiguo.
- `PedidoGuardado` conserva los importes históricos y expone `fechaTexto` en `dd/MM/yyyy HH:mm`.
- `FormatoPrecio.aPrecio()` produce pesos colombianos (`$29.000`) de forma idéntica en JVM y Kotlin/Native.
- `OrderBotContainer` es `expect/actual`: cada plataforma entrega el driver SQLite y el almacén de preferencias concretos.

### UI por plataforma

| Plataforma | Navegación | Puente con `shared` |
|---|---|---|
| Android | `NavHost` + `Rutas` | `collectAsState()` sobre los `StateFlow` |
| iOS | `NavigationStack` + `Ruta` | `EstadoPedido`/`EstadoTema` (`ObservableObject`) sobre `observarCambios(listener:)` |

Los enum de Kotlin se comparan en Swift con `===` y tienen ayudas como `esClaro`, `esOscuro` y `esSistema` en `Util/Interop+Shared.swift`.

## Persistencia e historial

SQLDelight crea dos tablas:

- `pedido`: número, dirección, método de pago, subtotal, envío, total y fecha.
- `itemPedido`: producto, cantidad y número de pedido (con borrado en cascada).

Las consultas `listarPedidos` e `itemsDePedido` alimentan la pantalla de historial. El historial se refresca al confirmar un pedido y al abrir la pantalla, y es accesible desde Inicio y Confirmación.

## Requisitos

- JDK 17.
- Android SDK 35 y Gradle Wrapper incluido.
- Xcode 15 o superior (validado con Xcode 26.6) para el objetivo iOS 16.

## Compilación y pruebas

```bash
# Pruebas de la capa compartida
./gradlew :shared:test

# Framework iOS para simulador arm64
./gradlew :shared:linkDebugFrameworkIosSimulatorArm64

# APK de depuración
./gradlew :androidApp:assembleDebug
```

Para iOS también puedes abrir `iosApp/iosApp.xcodeproj` en Xcode y ejecutar el esquema `iosApp`. El *Run Script* del proyecto invoca `embedAndSignAppleFrameworkForXcode`, así que el framework compartido se genera automáticamente.

Si prefieres terminal:

```bash
cd iosApp
xcodebuild -scheme iosApp \
  -destination 'platform=iOS Simulator,name=iPhone 16,OS=18.4' \
  -derivedDataPath ../build/ios-derived \
  build
```

## Localización

Las mismas claves se mantienen en tres lugares:

- `shared/src/commonMain/kotlin/.../util/Textos.kt` (catálogo de respaldo en `commonMain`).
- `androidApp/src/main/res/values/strings.xml`.
- `iosApp/iosApp/Resources/Localizable.xcstrings`.

Al añadir una clave nueva, actualiza los tres archivos.

## Estado conocido

- La acción **“Seguir pedido en vivo”** permanece sin conexión, igual que en la versión Swift original.
- Firestore no está habilitado; solo se conserva el DTO `PedidoRemoto` y su documentación.

Consulta `CONTRIBUTING.md` antes de crear un commit y `CHANGELOG.md` para el historial de cambios recientes.
