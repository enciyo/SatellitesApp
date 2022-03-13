package com.enciyo.satellitesapp.ui.ext

fun <T : Any> Boolean.take(takeTrue: T, takeFalse: T): T {
    return if (this) takeTrue else takeFalse
}
