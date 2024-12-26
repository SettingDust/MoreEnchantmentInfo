plugins {
    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.plugin.serialization)

    alias(catalog.plugins.architectury.plugin)
    alias(catalog.plugins.architectury.loom)
}

val id: String by rootProject.properties

architectury {
    forge()
    platformSetupLoomIde()
}

dependencies {
    forge(catalog.lexforge)
    minecraft(catalog.minecraft)
    mappings(loom.officialMojangMappings())
}

loom {
//    accessWidenerPath = project(":xplat").file("src/main/resources/$id.accesswidener")

    forge {
        convertAccessWideners = true

        mixinConfig("$id.forge.mixins.json")
    }

    addRemapConfiguration("modForgeRuntimeLibrary") {
        targetConfigurationName = "forgeRuntimeLibrary"
        onCompileClasspath = true
        onRuntimeClasspath = true
    }

    mixin {
        defaultRefmapName = "$id.forge.refmap.json"
    }
}

dependencies {
    catalog.mixinextras.common.let {
        compileOnly(it)
        annotationProcessor(it)
    }

    implementation(catalog.mixinextras.lexforge)

    implementation(project(":xplat", "transformProductionForge")) { isTransitive = false }
    include(project(":xplat")) { isTransitive = false }

    modImplementation(catalog.sinytra.connector)
    modImplementation(catalog.forgified.fabric.api) { exclude(module = "fabric-loader") }
    implementation(catalog.kotlin.forge)

    catalog.mixin.constraints.let {
        include(it)
        implementation(it)
    }

    modImplementation(catalog.yacl.forge) {
        exclude(module = "kotlin-stdlib")
        exclude(module = "annotations")
        exclude(module = "gson")
    }
}