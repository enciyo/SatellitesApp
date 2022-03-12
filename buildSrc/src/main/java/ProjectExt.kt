import depend.Dependencies
import org.gradle.api.IllegalDependencyNotation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

fun Project.addHilt() {
    plugins.apply(PluginIds.hilt)
    dependencies {
        implementation(Dependencies.hilt)
        kapt(Kapt.hilt)
    }
}

fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

fun DependencyHandlerScope.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}