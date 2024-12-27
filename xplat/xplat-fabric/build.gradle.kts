plugins {
    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.plugin.serialization)

    alias(catalog.plugins.architectury.plugin)
    alias(catalog.plugins.architectury.loom)
}

val id: String by rootProject.properties

architectury {
    fabric()
}

dependencies {
    minecraft(catalog.minecraft)
    mappings(loom.officialMojangMappings())

    modImplementation(catalog.fabric.loader)
    modImplementation(catalog.fabric.api)
    modImplementation(catalog.fabric.kotlin)

    implementation(catalog.mixin.constraints)

    modImplementation(catalog.yacl.fabric)

    modImplementation(catalog.jei.fabric)

    modImplementation(catalog.enchantment.descriptions.fabric)
    modImplementation(catalog.bookshelf.fabric)
}

sourceSets {
    main {
        java.srcDir(project(":xplat").sourceSets.main.get().java)
        kotlin.srcDir(project(":xplat").sourceSets.main.get().kotlin)
        resources.srcDir(project(":xplat").sourceSets.main.get().resources)
    }
}

loom {
//    accessWidenerPath = project(":xplat").file("src/main/resources/$id.accesswidener")

    mixin {
        defaultRefmapName = "$id.refmap.json"
    }
}