plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("module-plugin")
    id("feature-plugin")
}

android {

    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    hilt {
        enableTransformForLocalTests = true
    }

    compileOptions {
        sourceCompatibility = Options.sourceCompatibility
        targetCompatibility = Options.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Options.jvmTarget
    }

    applicationVariants.forEach {
        it.outputs.all {
            if (name.contains("release"))
                (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                    "apk/$name.apk"
        }
    }

}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}