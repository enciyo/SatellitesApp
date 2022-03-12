package plugin

import depend.AndroidTestDependencies
import depend.Dependencies
import depend.TestDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.dependencies {
            add("implementation", Dependencies.coreKtx)
            add("implementation", Dependencies.appCompat)
            add("implementation", Dependencies.material)
            add("implementation", Dependencies.constraintLayout)
            add("testImplementation", TestDependencies.junit)
            add("androidTestImplementation", AndroidTestDependencies.jUnit)
            add("androidTestImplementation", AndroidTestDependencies.espresso)
        }
    }
}