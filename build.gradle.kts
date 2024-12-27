import groovy.lang.Closure

plugins {
    java
    alias(catalog.plugins.kotlin.jvm) apply false
    alias(catalog.plugins.kotlin.plugin.serialization) apply false

    alias(catalog.plugins.git.version)

    alias(catalog.plugins.explosion) apply false

    alias(catalog.plugins.shadow)
}

val archive_name: String by rootProject.properties
val id: String by rootProject.properties

group = "settingdust.lazyyyyy"

val gitVersion: Closure<String> by extra
version = gitVersion()

base {
    archivesName = archive_name
}

allprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        withSourcesJar()
    }

    repositories {
        exclusiveContent {
            forRepository {
                maven("https://cursemaven.com")
            }
            filter {
                includeGroup("curse.maven")
            }
        }

        exclusiveContent {
            forRepository {
                maven("https://api.modrinth.com/maven")
            }
            filter {
                includeGroup("maven.modrinth")
            }
        }

        maven("https://maven.su5ed.dev/releases") {
            content {
                includeGroupAndSubgroups("dev.su5ed")
                includeGroupAndSubgroups("org.sinytra")
            }
        }

        maven("https://thedarkcolour.github.io/KotlinForForge/") {
            content { includeGroup("thedarkcolour") }
        }

        maven("https://maven.terraformersmc.com/") {
            content { includeGroupAndSubgroups("com.terraformersmc") }
        }

        maven("https://maven.bawnorton.com/releases") {
            content {
                includeGroup("com.github.bawnorton.mixinsquared")
            }
        }

        maven("https://modmaven.dev") {
            content {
                includeGroup("mezz.jei")
            }
        }

        maven("https://maven.isxander.dev/releases")

        mavenCentral()

        maven("https://maven.minecraftforge.net/")

        mavenLocal()
    }

    tasks {
        withType<ProcessResources> {
            val properties = mapOf(
                "id" to id,
                "version" to rootProject.version,
                "group" to rootProject.group,
                "name" to rootProject.name,
                "description" to rootProject.property("description").toString(),
                "author" to rootProject.property("author").toString(),
                "source" to rootProject.property("source").toString(),
                "fabric_loader" to ">=0.15",
                "minecraft" to ">=1.20.1",
                "fabric_kotlin" to "*"
            )

            inputs.properties(properties)

            filesMatching(
                listOf(
                    "pack.mcmeta",
                    "fabric.mod.json",
                    "META-INF/neoforge.mods.toml",
                    "META-INF/mods.toml",
                    "*.mixins.json",
                    "META-INF/MANIFEST.MF"
                )
            ) {
                expand(properties)
            }
        }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    base { archivesName.set("${rootProject.base.archivesName.get()}${project.path.replace(":", "-")}") }
}

dependencies {
    shadow(project(":lexforge")) {
        isTransitive = false
    }
    shadow(project(":fabric")) {
        isTransitive = false
    }
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        configurations = listOf(project.configurations.shadow.get())
        mergeServiceFiles()
        archiveClassifier.set("")

        doFirst {
            manifest {
                from(
                    configurations
                        .flatMap { it.files }
                        .map { zipTree(it) }
                        .map { zip -> zip.find { it.name.equals("MANIFEST.MF") } }
                )
            }
        }
    }

    build {
        dependsOn(shadowJar)
    }

    named<Jar>("sourcesJar") {
        from(project(":xplat").sourceSets.main.get().allSource)
        from(project(":lexforge").sourceSets.main.get().allSource)
        from(project(":fabric").sourceSets.main.get().allSource)
    }
}