import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.plugin.serialization)

    alias(catalog.plugins.architectury.plugin)
    alias(catalog.plugins.architectury.loom)
}

val id: String by rootProject.properties

architectury {
    platformSetupLoomIde()
    fabric()
}

dependencies {
    minecraft(catalog.minecraft)
    mappings(loom.officialMojangMappings())

    implementation(project(":xplat", "namedElements")) { isTransitive = false }
    include(project(":xplat:xplat-fabric")) { isTransitive = false }

    modImplementation(catalog.fabric.loader)
    modImplementation(catalog.fabric.api)
    modImplementation(catalog.fabric.kotlin)

    implementation(catalog.mixin.constraints)

    modImplementation(catalog.yacl.fabric)

    modImplementation(catalog.jei.fabric)

    modImplementation(catalog.enchantment.descriptions.fabric)
    modImplementation(catalog.bookshelf.fabric)

    modImplementation(catalog.emi.fabric)
}

tasks {
    withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    withType<ProcessResources> {
        from(rootProject.sourceSets.main.get().resources)
    }
}

loom {
//    accessWidenerPath = file("src/main/resources/$id.accesswidener")

    mixin {
        defaultRefmapName = "$id.refmap.json"
    }
}