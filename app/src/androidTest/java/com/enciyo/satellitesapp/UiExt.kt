package com.enciyo.satellitesapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.uiautomator.By
import androidx.test.uiautomator.SearchCondition
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until


const val BASIC_SAMPLE_PACKAGE = "com.enciyo.satellitesapp"
const val PAC_RECYCLER_VIEW = "androidx.recyclerview.widget.RecyclerView"

fun Int.toId(): String {
    val context = ApplicationProvider.getApplicationContext<Context>()
    return context.resources.getResourceEntryName(this)
}

fun visible(resourceId: String): SearchCondition<UiObject2>? {
    return Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, resourceId))
}

fun UiDevice.findResourceId(resourceId: String): UiObject2 {
    return this.findObject(By.res(BASIC_SAMPLE_PACKAGE, resourceId))
}

fun UiDevice.findResourceId(resourceId: Int): UiObject2 {
    return this.findObject(By.res(BASIC_SAMPLE_PACKAGE, resourceId.toId()))
}


inline fun <reified T> findChildFromRecyclerView(matchText: String): UiObject {
    return UiScrollable(UiSelector().className(PAC_RECYCLER_VIEW))
        .getChildByText(UiSelector().className(T::class.java.canonicalName), matchText)
}