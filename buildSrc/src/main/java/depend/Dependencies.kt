package depend

import Versions

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val navigationComponent = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    const val navigationComponentUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
    const val viewBindingPropertyDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingPropertyDelegate}"
    const val arrow  = "io.arrow-kt:arrow-core:${Versions.arrow}"
}

