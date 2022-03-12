import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
}

gradlePlugin {
    plugins {
        register("module-plugin") {
            id = "module-plugin"
            implementationClass = "plugin.CommonModulePlugin"
        }

        register("feature-plugin"){
            id = "feature-plugin"
            implementationClass = "plugin.FeatureModulePlugin"
        }
    }
}

