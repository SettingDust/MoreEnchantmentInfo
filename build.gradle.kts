import groovy.lang.Closure

plugins {
    java

    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"

    id("com.palantir.git-version") version "3.1.0"

    id("com.gradleup.shadow") version "8.3.6"

    id("earth.terrarium.cloche") version "0.8.6"
}

val archive_name: String by rootProject.properties
val id: String by rootProject.properties
val source: String by rootProject.properties

group = "settingdust.more_enchantment_info"

val gitVersion: Closure<String> by extra
version = gitVersion()

base {
    archivesName = archive_name
}

repositories {
    exclusiveContent {
        forRepository {
            maven("https://api.modrinth.com/maven")
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }

    exclusiveContent {
        forRepository {
            maven("https://maven.su5ed.dev/releases")
        }
        filter {
            includeGroupAndSubgroups("dev.su5ed")
            includeGroupAndSubgroups("org.sinytra")
        }
    }

    maven("https://modmaven.dev") {
        content {
            includeGroup("mezz.jei")
        }
    }

    maven("https://thedarkcolour.github.io/KotlinForForge/") {
        content {
            includeGroup("thedarkcolour")
        }
    }

    mavenCentral()

    maven("https://maven.fabricmc.net/")

    maven("https://maven.minecraftforge.net/")

    maven("https://maven.msrandom.net/repository/root")

    mavenLocal()
}

cloche {
    metadata {
        modId = id
        name = rootProject.property("name").toString()
        description = rootProject.property("description").toString()
        license = "CC-BY-SA 4.0"
        icon = "assets/$id/icon.png"
        sources = source
        issues = "$source/issues"
        author("SettingDust")
        dependency {
            modId = "minecraft"
            version {
                start = "1.20.1"
            }
        }
    }

    minecraftVersion = "1.20.1"

    mappings {
        official()
        parchment("2023.09.03")
    }

    common {
    }

    fabric {
        loaderVersion = "0.16.10"

        includedClient()

        metadata {
            dependencies {
                dependency {
                    modId = "fabric-api"
                }
            }
        }

        runs {
            client()
        }

        dependencies {
            fabricApi("0.92.3+1.20.1")

            modCompileOnlyApi("mezz.jei:jei-1.20.1-fabric-api:15.20.0.106")
            modRuntimeOnly("mezz.jei:jei-1.20.1-fabric:15.20.0.106")

            modImplementation("net.fabricmc:fabric-language-kotlin:1.13.1+kotlin.2.1.10") {
                isTransitive = false
            }
        }
    }

    forge {
        loaderVersion = "47.3.29"

        metadata {
            dependency {
                modId = "fabric_api"
            }
        }

        runs {
            client()
        }

        dependencies {
            implementation("thedarkcolour:kotlinforforge:4.10.0")

            modImplementation("dev.su5ed.sinytra.fabric-api:fabric-api:0.92.2+1.11.11+1.20.1")

            modImplementation("mezz.jei:jei-1.20.1-forge:15.20.0.106")
        }
    }
}