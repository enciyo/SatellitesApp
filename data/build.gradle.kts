plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("module-plugin")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Options.sourceCompatibility
        targetCompatibility = Options.sourceCompatibility
    }
    kotlinOptions {
        jvmTarget = Options.jvmTarget
    }
}

dependencies {
    implementation(depend.Dependencies.moshi)
    implementation(depend.Dependencies.room)
    implementation(project(mapOf("path" to ":domain")))
    kapt(Kapt.room)
    implementation(project(":shared"))
}