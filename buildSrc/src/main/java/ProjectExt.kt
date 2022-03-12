import depend.Dependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.addHilt() {
    plugins.apply(PluginIds.hilt)
    dependencies {
        add("implementation", Dependencies.hilt)
        add("kapt", Kapt.hilt)
    }
}