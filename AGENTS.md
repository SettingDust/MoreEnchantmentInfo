# AGENTS.md

## Project Overview

MoreEnchantmentInfo is a Kotlin-based Minecraft mod that adds enchantment information directly into JEI.
The repository targets multiple loaders and multiple Minecraft versions from a single Gradle build using Cloche.

Core stack:
- Kotlin JVM (Kotlin 2.3.20)
- Gradle Kotlin DSL
- Cloche multi-target Minecraft build tooling
- Fabric, Forge, and NeoForge targets

## Repository Layout

- `src/common/common/main`: Shared logic used across loaders and versions.
- `src/common/20.1/main`, `src/common/21.1/main`, `src/common/26.1/main`: Version-specific adapters.
- `src/fabric/**`: Fabric entrypoints and loader-specific adapters.
- `src/forge/**`: Forge entrypoints and loader-specific adapters.
- `src/neoforge/**`: NeoForge entrypoints and loader-specific adapters.
- `build.gradle.kts`: Main multi-target build configuration.
- `.github/workflows/build.yml`: CI build (`gradlew build`).
- `.github/workflows/publish.yml`: Release publishing workflow.

## Environment Setup

Prerequisites:
- JDK 21 available on PATH (CI uses Temurin 21).
- Use the provided Gradle wrapper.

Common bootstrap commands:

```bash
# Linux/macOS
./gradlew tasks --all
./gradlew build
```

```powershell
# Windows
.\gradlew.bat tasks --all
.\gradlew.bat build
```

## Development Workflow

Primary commands:

```bash
./gradlew build
./gradlew check
./gradlew test
./gradlew printVersion
```

Useful artifact commands:

```bash
./gradlew shadowContainersJar
./gradlew shadowSourcesJar
```

Clean rebuild:

```bash
./gradlew clean build
```

## Run Configurations (Minecraft Clients)

Use these Gradle tasks to launch development clients:

```bash
./gradlew runVersionFabric201Client
./gradlew runVersionFabric211Client
./gradlew runVersionFabric261Client
./gradlew runVersionForge201Client
./gradlew runVersionNeoforge211Client
./gradlew runVersionNeoforge261Client
```

Notes:
- First run can be slow because assets/natives are resolved and extracted.
- The `run/` directory is local runtime state and should not be relied on for reproducible changes.

## Testing Guidance

Run:

```bash
./gradlew test
./gradlew check
```

Current state:
- No dedicated test source tree is present yet under `src/**/test`.
- `test` still runs Gradle's test lifecycle and should stay green.

When contributing logic changes:
- Add tests if you introduce testable pure logic.
- For loader/version integration changes, validate at least one relevant `runVersion*Client` target.

## Code Style and Conventions

- Kotlin code style is `official` (see `gradle.properties`).
- Keep shared behavior in `src/common/common/main`.
- Put version-specific API differences in `src/common/<version>/main` adapters.
- Put loader-specific integration in `src/fabric`, `src/forge`, `src/neoforge`.
- Prefer small adapter layers over branching shared logic by loader.

## Build and Release Notes

- CI and release pipelines call `gradlew build`.
- Publish workflow expects built jars under `build/libs/` and publishes to Modrinth/CurseForge.
- Version output comes from git-based versioning; use `./gradlew printVersion` to inspect.

## Pull Request Checklist

Before opening a PR:

```bash
./gradlew clean build
./gradlew test
```

Validation expectations:
- Ensure modified targets compile for affected loaders/versions.
- If touching runtime behavior, run at least one matching `runVersion*Client` task.
- Update docs when changing workflows or task names.

## Security and Secrets

- Do not commit tokens or credentials.
- Publishing uses GitHub Actions secrets (for example Modrinth/CurseForge tokens).
- Keep local overrides and machine-specific files out of commits.

## Agent-Specific Tips

- Prefer Gradle task names exactly as shown by `tasks --all`; this project has many generated target tasks.
- Avoid editing generated outputs under `build/`.
- Make minimal, target-scoped changes: shared code first, then version adapter, then loader adapter if needed.