package plugin

import addHilt
import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("kotlin-kapt")
        target.addHilt()
    }
}

