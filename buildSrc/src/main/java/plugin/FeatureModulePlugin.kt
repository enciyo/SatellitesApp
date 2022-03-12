package plugin

import depend.AndroidTestDependencies
import depend.Dependencies
import depend.TestDependencies
import implementation
import testImplementation
import androidTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import PluginIds

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(PluginIds.hilt)
        target.plugins.apply(PluginIds.navigationComponent)
        target.dependencies {
            implementation(Dependencies.coreKtx)
            implementation(Dependencies.appCompat)
            implementation(Dependencies.material)
            implementation(Dependencies.constraintLayout)
            implementation(Dependencies.navigationComponent)
            implementation(Dependencies.navigationComponentUi)
            testImplementation(TestDependencies.junit)
            androidTestImplementation(AndroidTestDependencies.jUnit)
            androidTestImplementation(AndroidTestDependencies.espresso)
        }
    }
}