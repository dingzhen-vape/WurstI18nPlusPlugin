# WurstI18nPlusPlugin Agent Guide

## Project scope

- This is a Fabric Loom mod that adds Wurst Client translations, with shared code in `src/main` and client-only code in `src/client`.
- The shared entrypoint is `com.wursti18nplugin.WurstI18nPlugin`; the client entrypoint is `com.wursti18nplugin.WurstI18nPluginClient`.
- `Translator` owns classpath translation loading and falls back from the requested language to `en_us`. Translation resources live under `src/main/resources/assets/wursti18nplugin/translations/`.
- Client behavior is currently implemented through the mixins listed in `src/client/resources/wursti18nplugin.client.mixins.json`. Keep client-only Wurst/Minecraft references in `src/client` or client mixins.

## Build and run

- Use the Gradle wrapper: `./gradlew build` for the full build and `./gradlew runClient` for the development client.
- The current Gradle configuration is authoritative for Minecraft, Fabric, Loom, and Java versions. At the time of writing it targets Minecraft `26.1.2` and Java `25`; the README may describe older versions.
- The Wurst Client jar is a local dependency declared in `build.gradle`. Before building, verify that the exact configured file exists under `libs/`; do not silently substitute a different Wurst or Minecraft version.
- There is no dedicated test suite configured. At minimum, run `./gradlew build` after Java, resource, mixin, or Gradle changes.

## Change conventions

- Preserve the existing package name `com.wursti18nplugin` and the mod id `wurst_i18n_plus_plugin`.
- Keep translation keys and values valid JSON. Add or update language entries in both `zh_cn.json` and `en_us.json` when introducing a new key, unless the fallback behavior is intentional.
- Keep resource paths aligned with the hard-coded translation path in `Translator`; changing either side requires checking packaged-resource loading.
- Treat `fabric.mod.json` and both mixin configuration files as runtime wiring. When changing a mixin class, update the matching configuration and verify its environment (`main` versus `client`).
- Avoid unrelated formatting or version updates. Do not commit generated files from `build/` or runtime diagnostic output such as `lackingTranslations/`.

## References

- See [README.md](README.md) for installation, dependency setup, and user-facing project notes.
- See [build.gradle](build.gradle) and [gradle.properties](gradle.properties) for the current build configuration.