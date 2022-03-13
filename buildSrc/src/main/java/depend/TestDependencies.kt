package depend

import Versions

object TestDependencies{
    const val junit = "junit:junit:${Versions.jUnit}"
    const val mockk = "io.mockk:mockk:1.12.3"
    const val androidxCore = "androidx.arch.core:core-testing:2.1.0"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
}