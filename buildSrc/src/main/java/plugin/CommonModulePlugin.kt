package plugin

import addHilt
import implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("kotlin-kapt")
        target.dependencies {
            implementation(depend.Dependencies.coroutine)
            implementation(depend.Dependencies.moshi)
        }
        target.addHilt()
    }
}



