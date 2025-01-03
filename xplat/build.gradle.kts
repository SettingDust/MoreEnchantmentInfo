import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.plugin.serialization)

    alias(catalog.plugins.architectury.plugin)
    alias(catalog.plugins.architectury.loom)
}

val id: String by rootProject.properties

subprojects {
    base { archivesName.set("${rootProject.base.archivesName.get()}-${project.name}") }
}

architectury {
    common("forge", "fabric")
}

dependencies {
    minecraft(catalog.minecraft)
    mappings(loom.officialMojangMappings())

    implementation(catalog.mixinsquared.common)
    annotationProcessor(catalog.mixinsquared.common)

    modImplementation(catalog.fabric.loader)
    modImplementation(catalog.fabric.api)
    modImplementation(catalog.fabric.kotlin)

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

    jar {
        manifest {
            attributes(
                "MixinConfigs" to "$id.mixins.json"
            )
        }
    }
}

loom {
//    accessWidenerPath = file("src/main/resources/$id.accesswidener")

    mixin {
        defaultRefmapName = "$id.refmap.json"
    }
}