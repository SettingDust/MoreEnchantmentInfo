# AGENTS.md

## Project Overview

MoreEnchantmentInfo is a Kotlin Minecraft mod that injects enchantment information into JEI.
This repository builds multiple loaders and Minecraft versions from a single Gradle Kotlin DSL project.

Core stack:
- Kotlin JVM (2.3.x)
- Gradle Kotlin DSL
- Cloche multi-target tooling
- Fabric, Forge, NeoForge

## Repository Layout

- `src/common/common/main`: shared logic for all loaders/versions
- `src/common/20.1/main`, `src/common/21.1/main`, `src/common/26.1/main`: version adapters
- `src/fabric/**`: Fabric-specific integration
- `src/forge/**`: Forge-specific integration
- `src/neoforge/**`: NeoForge-specific integration
- `.github/workflows/build.yml`: CI build (`gradlew build`)
- `.github/workflows/publish.yml`: release publishing

## Setup Commands

Prerequisites:
- JDK 21 on PATH (CI uses Temurin 21)
- Use Gradle wrapper from repo root

Windows:

```powershell
.\gradlew.bat tasks --all
.\gradlew.bat build
```

Linux/macOS:

```bash
./gradlew tasks --all
./gradlew build
```

## Development Workflow

Primary commands:

```bash
./gradlew build
./gradlew check
./gradlew test
./gradlew printVersion
```

Loader container and merged artifacts:

```bash
./gradlew shadowMergedJar shadowMergedDevJar shadowSourcesJar
```

Notes for packaging changes:
- Final root jar should keep loader outputs in nested containers under `META-INF/jars/`.
- Avoid flattening Forge/NeoForge container jars into the root jar.
- NeoForge currently uses a merged inner jar for runtime compatibility across supported NeoForge versions.

Clean rebuild:

```bash
./gradlew clean build
```

## Run Configurations

Use these tasks for manual runtime validation:

```bash
./gradlew runVersionFabric201Client
./gradlew runVersionFabric211Client
./gradlew runVersionFabric261Client
./gradlew runVersionForge201Client
./gradlew runVersionNeoforge211Client
./gradlew runVersionNeoforge261Client
```

First launch may be slow due to assets/natives resolution.

## Testing Instructions

Repository-wide checks:

```bash
./gradlew test
./gradlew check
./gradlew build
```

Current testing state:
- There is no dedicated test tree under `src/**/test` yet.
- Keep Gradle test lifecycle green even when no tests are present.

When changing behavior:
- Add tests for pure logic where feasible.
- For loader/version integration updates, run at least one relevant `runVersion*Client` task.
- For packaging updates, validate produced jars in `build/libs/` (nested jars + metadata consistency).

## Code Style and Conventions

- Kotlin style is `official` (see `gradle.properties`).
- Keep cross-loader logic in `src/common/common/main`.
- Prefer version adapters over branching shared logic.
- Keep loader-specific code isolated under loader source roots.
- Make minimal, target-scoped changes.

## Build and Deployment

- CI build job runs `gradlew build` on JDK 21.
- Release workflow builds first, then publishes jars from `build/libs/` to Modrinth/CurseForge.
- Use `./gradlew printVersion` to inspect git-derived versioning before release checks.

## Pull Request Checklist

Before opening a PR:

```bash
./gradlew clean build
./gradlew test
```

Also verify:
- affected loader/version targets compile
- packaging changes preserve expected nested-jar layout
- docs are updated if task names/workflow changed

## Security and Secrets

- Never commit credentials/tokens.
- Publishing uses GitHub Actions secrets.
- Keep machine-local overrides out of commits.

## Troubleshooting

- If generated metadata in packaged jars is wrong, inspect transformer/task state sharing and task-specific outputs.
- If loader classes leak across boundaries, inspect whether container outputs were accidentally unzipped into root artifacts.
- Do not edit generated files under `build/`; update source/configuration instead.

## Agent-Specific Tips

- Prefer exact Gradle task names from `tasks --all`.
- Validate both normal and dev artifact variants when modifying packaging (`shadowMergedJar` and `shadowMergedDevJar`).
- Keep edits focused: shared code first, then version adapter, then loader adapter.