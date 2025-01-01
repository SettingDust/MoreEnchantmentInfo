plugins {
    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.plugin.serialization)

    alias(catalog.plugins.architectury.plugin)
    alias(catalog.plugins.architectury.loom)
}

val id: String by rootProject.properties

architectury {
    forge()
}

dependencies {
    forge(catalog.lexforge)
    minecraft(catalog.minecraft)
    mappings(loom.officialMojangMappings())

    implementation(project(":xplat", "namedElements")) { isTransitive = false }

    implementation(catalog.sinytra.connector)
    modImplementation(catalog.forgified.fabric.api) {
        exclude(module = "fabric-loader")
    }
    implementation(catalog.kotlin.forge)

    implementation(catalog.mixin.constraints)
    implementation(catalog.mixinextras.common)
    annotationProcessor(catalog.mixinextras.common)

    modImplementation(catalog.yacl.lexforge)

    modImplementation(catalog.jei.lexforge)

    modImplementation(catalog.enchantment.descriptions.lexforge)
    modImplementation(catalog.bookshelf.lexforge)

    modImplementation(catalog.emi.lexforge)
}

sourceSets {
    main {
        java.srcDir(project(":xplat").sourceSets.main.get().java)
        kotlin.srcDir(project(":xplat").sourceSets.main.get().kotlin)
        resources.srcDir(project(":xplat").sourceSets.main.get().resources)
    }
}

tasks {
    jar {
        manifest {
            attributes(
                "MixinConfigs" to "$id.mixins.json"
            )
        }
    }
}

loom {
//    accessWidenerPath = project(":xplat").file("src/main/resources/$id.accesswidener")

    mixin {
        defaultRefmapName = "$id.refmap.json"
    }
}