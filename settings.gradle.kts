dependencyResolutionManagement {
    pluginManagement {
        repositories {
            mavenCentral()
            gradlePluginPortal()
            maven("https://maven.msrandom.net/repository/cloche")
            maven("https://raw.githubusercontent.com/SettingDust/cloche/refs/heads/maven-repo/")
            maven("https://raw.githubusercontent.com/SettingDust/minecraft-codev/refs/heads/maven-repo/")
            mavenLocal()
        }
    }
}

object VersionFormats {
    val versionPlusMc = { mcVer: String, ver: String -> "$ver+$mcVer" }
    val mcDashVersion = { mcVer: String, ver: String -> "$mcVer-$ver" }
}

object VersionTransformers {
    val versionDashLoader = { ver: String, variant: String, _: String -> "$ver-$variant" }
    val loaderUnderlineVersion = { ver: String, variant: String, _: String -> "${variant}_$ver" }
    val versionDashLoaderCommaMcVersion =
        { ver: String, variant: String, mcVersion: String -> "$ver-$variant,$mcVersion" }
}

object ArtifactTransformers {
    val artifactDashLoaderDashMcVersion =
        { artifact: String, variant: String, mcVersion: String -> "$artifact-$variant-$mcVersion" }
    val artifactDashMcVersionDashLoader =
        { artifact: String, variant: String, mcVersion: String -> "$artifact-$mcVersion-$variant" }
    val artifactDashLoader = { artifact: String, variant: String, _: String -> "$artifact-$variant" }
}

open class VariantConfig(
    val artifactTransformer: (artifact: String, variant: String, mcVersion: String) -> String = { artifact, _, _ -> artifact },
    val versionTransformer: (version: String, variant: String, mcVersion: String) -> String = { ver, _, _ -> ver }
) {
    companion object : VariantConfig()
}

data class VariantMapping(
    val mcVersion: String,
    val loaders: Map<String, VariantConfig>
)

fun VersionCatalogBuilder.modrinth(
    id: String,
    artifact: String = id,
    mcVersionToVersion: Map<String, String>,
    versionFormat: (String, String) -> String = { _, v -> v },
    mapping: List<VariantMapping> = emptyList()
) {
    val allLoaders = mapping.flatMap { it.loaders.keys }.toSet()
    val isSingleLoader = allLoaders.size == 1
    val isSingleMcVersion = mcVersionToVersion.size == 1

    if (isSingleMcVersion) {
        val (mcVersion, modVersion) = mcVersionToVersion.entries.single()
        val config = mapping.find { it.mcVersion == mcVersion }
            ?: error("No loader config found for MC $mcVersion")

        val version = versionFormat(mcVersion, modVersion)

        config.loaders.forEach { (loaderName, loader) ->
            library(
                if (isSingleLoader) "$id"
                else "${id}_$loaderName",
                "maven.modrinth",
                loader.artifactTransformer(artifact, loaderName, mcVersion)
            ).version(loader.versionTransformer(version, loaderName, mcVersion))
        }
        return
    }

    mcVersionToVersion.forEach { (mcVersion, modVersion) ->
        val config = mapping.find { it.mcVersion == mcVersion }
            ?: error("No loader config found for MC $mcVersion")

        val version = versionFormat(mcVersion, modVersion)

        config.loaders.forEach { (loaderName, loader) ->
            library(
                if (isSingleLoader) "${id}_${mcVersion}"
                else "${id}_${mcVersion}_$loaderName",
                "maven.modrinth",
                loader.artifactTransformer(artifact, loaderName, mcVersion)
            ).version(loader.versionTransformer(version, loaderName, mcVersion))
        }
    }
}

fun VersionCatalogBuilder.maven(
    id: String,
    group: String,
    artifact: String = id,
    mcVersionToVersion: Map<String, String>,
    versionFormat: (String, String) -> String = { _, v -> v },
    mapping: List<VariantMapping> = emptyList()
) {
    val allLoaders = mapping.flatMap { it.loaders.keys }.toSet()
    val isSingleLoader = allLoaders.size == 1
    val isSingleMcVersion = mcVersionToVersion.size == 1

    if (isSingleMcVersion) {
        val (mcVersion, modVersion) = mcVersionToVersion.entries.single()
        val config = mapping.find { it.mcVersion == mcVersion }
            ?: error("No loader config found for MC $mcVersion")

        val version = versionFormat(mcVersion, modVersion)

        config.loaders.forEach { (loaderName, loader) ->
            library(
                if (isSingleLoader) id
                else "${id}_$loaderName",
                group,
                loader.artifactTransformer(artifact, loaderName, mcVersion)
            ).version(loader.versionTransformer(version, loaderName, mcVersion))
        }
        return
    }

    mcVersionToVersion.forEach { (mcVersion, baseVersion) ->
        val config = mapping.find { it.mcVersion == mcVersion }
            ?: error("No loader config found for MC $mcVersion")

        val version = versionFormat(mcVersion, baseVersion)

        config.loaders.forEach { (loaderName, loader) ->
            library(
                if (mcVersion == "*") {
                    if (isSingleLoader) id
                    else "${id}_$loaderName"
                } else {
                    if (isSingleLoader) "${id}_${mcVersion}"
                    else "${id}_${mcVersion}_$loaderName"
                },
                group,
                loader.artifactTransformer(artifact, loaderName, mcVersion)
            ).version(loader.versionTransformer(version, loaderName, mcVersion))
        }
    }
}

dependencyResolutionManagement.versionCatalogs.create("catalog") {
    maven(
        id = "mixinextras",
        group = "io.github.llamalad7",
        artifact = "mixinextras",
        mcVersionToVersion = mapOf("*" to "0.5.0"),
        mapping = listOf(
            VariantMapping(
                "*", mapOf(
                    "forge" to VariantConfig(ArtifactTransformers.artifactDashLoader),
                    "fabric" to VariantConfig(ArtifactTransformers.artifactDashLoader),
                    "common" to VariantConfig(ArtifactTransformers.artifactDashLoader)
                )
            )
        )
    )

    maven(
        id = "jei",
        group = "mezz.jei",
        artifact = "jei",
        mcVersionToVersion = mapOf(
            "1.20.1" to "15.20.0.112",
            "1.21.1" to "19.22.1.316"
        ),
        mapping = listOf(
            VariantMapping(
                "1.20.1", mapOf(
                    "forge" to VariantConfig(ArtifactTransformers.artifactDashMcVersionDashLoader),
                    "fabric" to VariantConfig(ArtifactTransformers.artifactDashMcVersionDashLoader)
                )
            ),
            VariantMapping(
                "1.21.1", mapOf(
                    "neoforge" to VariantConfig(ArtifactTransformers.artifactDashMcVersionDashLoader),
                    "fabric" to VariantConfig(ArtifactTransformers.artifactDashMcVersionDashLoader)
                )
            )
        )
    )

    modrinth(
        id = "enchantmentDescriptions",
        artifact = "enchantment-descriptions",
        mcVersionToVersion = mapOf(
            "1.20.1" to "17.1.19",
            "1.21.1" to "21.1.8"
        ),
        mapping = listOf(
            VariantMapping(
                "1.20.1", mapOf(
                    "forge" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                    "fabric" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                )
            ),
            VariantMapping(
                "1.21.1", mapOf(
                    "neoforge" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                    "fabric" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                )
            )
        )
    )

    modrinth(
        id = "bookshelf",
        artifact = "bookshelf-lib",
        mcVersionToVersion = mapOf(
            "1.20.1" to "20.2.13",
            "1.21.1" to "21.1.69"
        ),
        mapping = listOf(
            VariantMapping(
                "1.20.1", mapOf(
                    "forge" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                    "fabric" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                )
            ),
            VariantMapping(
                "1.21.1", mapOf(
                    "neoforge" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                    "fabric" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                )
            )
        )
    )

    modrinth(
        id = "prickle",
        artifact = "prickle",
        mcVersionToVersion = mapOf(
            "1.21.1" to "21.1.11"
        ),
        mapping = listOf(
            VariantMapping(
                "1.21.1", mapOf(
                    "neoforge" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                    "fabric" to VariantConfig(versionTransformer = VersionTransformers.versionDashLoaderCommaMcVersion),
                )
            )
        )
    )
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "MoreEnchantmentInfo"