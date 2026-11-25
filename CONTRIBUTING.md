# Contribuir a OrderBot KMP

Gracias por trabajar en OrderBot. Este documento resume el flujo mínimo antes de abrir un Pull Request.

## Idioma

La documentación y los mensajes de commit se escriben en español.

## Ramas

Usa nombres cortos y descriptivos:

- `feature/nombre-funcionalidad`
- `fix/nombre-error`
- `docs/nombre-documento`
- `refactor/nombre-modulo`

## Antes de hacer commit

Ejecuta las comprobaciones locales:

```bash
./gradlew :shared:test \
  :shared:linkDebugFrameworkIosSimulatorArm64 \
  :androidApp:assembleDebug
```

Y, si cambios Swift o recursos de iOS:

```bash
cd iosApp
xcodebuild -scheme iosApp \
  -destination 'platform=iOS Simulator,name=iPhone 16,OS=18.4' \
  -derivedDataPath ../build/ios-derived \
  build
```

## Mensajes de commit

Prefiere un prefijo y una descripción imperativa:

- `feat: añade filtro por fecha al historial`
- `fix: corrige el total de pedidos históricos`
- `docs: documenta la arquitectura compartida`
- `test: cubre la lectura de pedidos guardados`

## Reglas para archivos generados

No versiones:

- `build/`, `.gradle/`, `.kotlin/`, `.idea/`.
- `local.properties` ni keystores (`*.jks`, `*.keystore`).
- `DerivedData` de Xcode ni `xcuserdata`.
- Archivos compilados o artefactos de IDE.

Si añades una clave de texto, actualiza `Textos.kt`, `strings.xml` y `Localizable.xcstrings` en el mismo commit.

## Pull Request

Incluye:

1. Qué cambia y por qué.
2. Pantallas o comandos para verificarlo.
3. Si afecta a Android, iOS o a la capa compartida.
4. Capturas cuando el cambio sea visual.
