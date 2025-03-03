enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    pluginManagement {
        repositories {
            maven("https://maven.architectury.dev/")
            maven("https://maven.neoforged.net/releases/")
            maven("https://maven.msrandom.net/repository/root")
            maven("https://maven.resourcefulbees.com/repository/maven-public/")
            maven("https://maven.fabricmc.net/")
            maven("https://maven.minecraftforge.net/")
            maven("https://maven2.bai.lol")
            mavenCentral()
            gradlePluginPortal()
            mavenLocal()
        }
    }
}

fun MinecraftVariant.fabricApi() = "$fabricApi+$minecraft"
fun KotlinVariant.fabricKotlin(): String {
    require(Versions.Minecraft.fabricKotlin.first == Versions.Kotlin.kotlin) { "Kotlin version mismatch for fabric kotlin" }
    return "${Versions.Minecraft.fabricKotlin.second}+kotlin.${Versions.Kotlin.kotlin}"
}

fun MinecraftVariant.lexforge() = "$minecraft-$lexforge"
fun MinecraftVariant.forgifiedFabricApi() = "$forgifiedFabricApi+$minecraft"
fun MinecraftVariant.connector() = "$connector+$minecraft"

@Suppress("ClassName")
object Versions {
    val cloche = "0.7.26"

    object Minecraft {

        /**
         * https://modrinth.com/mod/fabric-language-kotlin/versions
         */
        val fabricKotlin = "2.1.10" to "1.13.1"

        /**
         * https://linkie.shedaniel.dev/dependencies?loader=fabric
         */
        val fabricLoader = "0.16.10"

        object V1_20 : MinecraftVariant {
            override val name = "1.20"

            override val minecraft = "1.20.1"


            /**
             * https://linkie.shedaniel.dev/dependencies?loader=fabric&version=1.20.1
             */
            override val fabricApi = "0.92.3"


            /**
             * https://linkie.shedaniel.dev/dependencies?loader=forge&version=1.20.1
             */
            override val lexforge = "47.3.29"

            /**
             * https://modrinth.com/mod/forgified-fabric-api/versions?g=1.20.1
             */
            override val forgifiedFabricApi = "0.92.2+1.11.11"

            /**
             * https://modrinth.com/mod/connector/versions?g=1.20.1
             */
            override val connector = "1.0.0-beta.46"

            /**
             * https://modrinth.com/mod/kotlin-for-forge/versions?g=1.20.1
             */
            override val forgeKotlin = "4.11.0"

            /**
             * https://modrinth.com/mod/jei/versions?g=1.20.1
             */
            override val jei = "15.20.0.106"

            /**
             * https://modrinth.com/mod/enchantment-descriptions/versions
             */
            override val enchantmentDescriptions = "17.1.19"

            /**
             * https://modrinth.com/mod/bookshelf-lib/versions?g=1.20.1
             */
            override val bookshelf = "20.2.13"
        }

        object V1_21 : MinecraftVariant {
            override val name = "1.21"

            override val minecraft = "1.21.1"


            /**
             * https://linkie.shedaniel.dev/dependencies?loader=fabric&version=1.21.1
             */
            override val fabricApi = "0.115.0"


            /**
             * https://linkie.shedaniel.dev/dependencies?loader=forge&version=1.21.1
             */
            override val lexforge = "52.0.47"

            /**
             * https://modrinth.com/mod/forgified-fabric-api/versions?g=1.21.1
             */
            override val forgifiedFabricApi = "0.107.0+2.0.23"

            /**
             * https://modrinth.com/mod/connector/versions?g=1.20.1
             */
            override val connector = "2.0.0-beta.6"

            /**
             * https://modrinth.com/mod/kotlin-for-forge/versions?g=1.21.1
             */
            override val forgeKotlin = "5.7.0"

            /**
             * https://modrinth.com/mod/jei/versions?g=1.20.1
             */
            override val jei = "19.21.0.247"

            /**
             * https://modrinth.com/mod/enchantment-descriptions/versions
             */
            override val enchantmentDescriptions = "21.1.5"

            /**
             * https://modrinth.com/mod/bookshelf-lib/versions?g=1.20.1
             */
            override val bookshelf = "21.1.48"
        }
    }

    object Kotlin : KotlinVariant {
        /**
         * https://kotlinlang.org/docs/releases.html#release-details
         */
        override val kotlin = "2.1.10"
    }
}

sealed interface MinecraftVariant {
    val name: String

    val minecraft: String

    val fabricApi: String

    val lexforge: String
    val forgifiedFabricApi: String
    val connector: String
    val forgeKotlin: String

    val jei: String

    val enchantmentDescriptions: String
    val bookshelf: String
}

sealed interface KotlinVariant {
    val kotlin: String
}

dependencyResolutionManagement.versionCatalogs.create("catalog") {

    plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").version(Versions.Kotlin.kotlin)
    plugin("kotlin-plugin-serialization", "org.jetbrains.kotlin.plugin.serialization").version(Versions.Kotlin.kotlin)

    library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").version(Versions.Kotlin.kotlin)

    val kotlinxSerialization = "1.7.3"
    library("kotlinx-serialization-core", "org.jetbrains.kotlinx", "kotlinx-serialization-core").version(
        kotlinxSerialization
    )
    library("kotlinx-serialization-json", "org.jetbrains.kotlinx", "kotlinx-serialization-json").version(
        kotlinxSerialization
    )

    library("kotlinx-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version("1.10.1")

    // https://modrinth.com/mod/kinecraft-serialization/versions
    library("kinecraft-serialization", "maven.modrinth", "kinecraft-serialization").version("1.16.0")

    library("mixin", "org.spongepowered", "mixin").version("0.8.7")
    val mixinextras = "0.5.0-beta.4"
    library("mixinextras-common", "io.github.llamalad7", "mixinextras-common").version(mixinextras)
    library("mixinextras-lexforge", "io.github.llamalad7", "mixinextras-forge").version(mixinextras)
    library("mixinextras-fabric", "io.github.llamalad7", "mixinextras-fabric").version(mixinextras)

    val mixinsquared = "0.2.0"
    library("mixinsquared-common", "com.github.bawnorton.mixinsquared", "mixinsquared-common").version(mixinsquared)
    library("mixinsquared-lexforge", "com.github.bawnorton.mixinsquared", "mixinsquared-forge").version(mixinsquared)
    library("mixinsquared-fabric", "com.github.bawnorton.mixinsquared", "mixinsquared-fabric").version(mixinsquared)

    library("mixin-constraints", "com.moulberry", "mixinconstraints").version("1.0.1")

    val mainVariant = Versions.Minecraft.V1_20

    library("fabric-loader", "net.fabricmc", "fabric-loader").version(Versions.Minecraft.fabricLoader)
    library("fabric-kotlin", "net.fabricmc", "fabric-language-kotlin").version(Versions.Kotlin.fabricKotlin())

    for (variant in Versions.Minecraft::class.nestedClasses.mapNotNull { it.objectInstance }
        .filterIsInstance<MinecraftVariant>()) {
        val suffix = if (variant == mainVariant) "" else "-${variant.minecraft}"

        fun library(name: String, group: String, artifact: String) = this.library("$name$suffix", group, artifact)

        library("minecraft", "com.mojang", "minecraft").version(variant.minecraft)
        library("fabric-api", "net.fabricmc.fabric-api", "fabric-api").version(variant.fabricApi())

        // https://linkie.shedaniel.dev/dependencies?loader=forge
        library("lexforge", "net.minecraftforge", "forge").version(variant.lexforge())
        library(
            "forgified-fabric-api",
            "dev.su5ed.sinytra.fabric-api",
            "fabric-api"
        ).version(variant.forgifiedFabricApi())
        library("sinytra-connector", "org.sinytra", "Connector").version(variant.connector())
        library("kotlin-forge", "thedarkcolour", "kotlinforforge").version(variant.forgeKotlin)

        library("yacl-fabric", "dev.isxander", "yet-another-config-lib").version("3.6.1+1.20.1-fabric")
        library("yacl-lexforge", "dev.isxander", "yet-another-config-lib").version("3.6.1+1.20.1-forge")

        library("jei-fabric", "mezz.jei", "jei-1.20.1-fabric").version(variant.jei)
        library("jei-lexforge", "mezz.jei", "jei-1.20.1-forge").version(variant.jei)

        library(
            "enchantment-descriptions-fabric",
            "maven.modrinth",
            "enchantment-descriptions"
        ).version("${variant.enchantmentDescriptions}-fabric")
        library(
            "enchantment-descriptions-lexforge",
            "maven.modrinth",
            "enchantment-descriptions"
        ).version("${variant.enchantmentDescriptions}-forge")

        library(
            "bookshelf-fabric",
            "maven.modrinth",
            "bookshelf-lib"
        ).version("${variant.bookshelf}-fabric")
        library(
            "bookshelf-lexforge",
            "maven.modrinth",
            "bookshelf-lib"
        ).version("${variant.bookshelf}-forge")

    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

val name: String by settings

rootProject.name = name

//include(":xplat")
//include(":xplat:xplat-lexforge")
//include(":xplat:xplat-fabric")
//
//include(":lexforge")
//include(":fabric")
//
//include(":xplat:xplat-1.21")
//include(":lexforge:lexforge-1.21")