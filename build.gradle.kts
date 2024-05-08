import dev.architectury.pack200.java.Pack200Adapter

buildscript {
    repositories {
        maven {
            name = "jitpack"
            url = uri("https://jitpack.io")
        }
    }
    dependencies {
        classpath("com.dipien:semantic-version-gradle-plugin:1.3.0")
    }
}

plugins {
    kotlin("jvm") version "1.9.21"
    id("gg.essential.loom")
    id("io.github.juuxel.loom-quiltflower")
    id("dev.architectury.architectury-pack200")
    id("com.github.johnrengelman.shadow")
}

group = "me.clicks"
version = "0.0.1"
apply(plugin = "com.dipien.semantic-version")

loom {
    runConfigs {
        named("client") {
            ideConfigGenerated(true)
        }
    }

    launchConfigs {
        getByName("client") {
            arg("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
            arg("--mixin", "mixins.warping.json")
        }
    }

    forge {
        pack200Provider.set(Pack200Adapter())
        mixinConfig("mixins.warping.json")
    }
}

val embed: Configuration by configurations.creating
configurations.implementation.get().extendsFrom(embed)

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")

    compileOnly("gg.essential:essential-1.8.9-forge:14616+g169bd9af6a")
    embed("gg.essential:loader-launchwrapper:1.1.3")

    compileOnly("org.spongepowered:mixin:0.8.5-SNAPSHOT")
    annotationProcessor("org.spongepowered:mixin:0.8.5-SNAPSHOT:processor")
    modRuntimeOnly("me.djtheredstoner:DevAuth-forge-legacy:1.2.0")
}

repositories {
    maven("https://repo.essential.gg/repository/maven-public")
    maven("https://repo.spongepowered.org/repository/maven-public")
    maven ("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

tasks {
    jar {
        from(embed.files.map { zipTree(it) })

        manifest.attributes(
            mapOf(
                "ModSide" to "CLIENT",
                "TweakClass" to "gg.essential.loader.stage0.EssentialSetupTweaker",
                "MixinConfigs" to "mixins.warping.json"
            )
        )
    }

    processResources {
        inputs.property("version", project.version)
        inputs.property("mcversion", "1.8.9")
        filesMatching("mcmod.info") {
            expand("version" to project.version, "mcversion" to "1.8.9")
        }
    }

    withType<JavaCompile> {
        options.release.set(8)
    }
}
