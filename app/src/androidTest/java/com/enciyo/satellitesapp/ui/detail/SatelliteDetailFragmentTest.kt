package com.enciyo.satellitesapp.ui.detail

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.enciyo.satellitesapp.BASIC_SAMPLE_PACKAGE
import com.enciyo.satellitesapp.findChildFromRecyclerView
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class SatelliteDetailFragmentTest {

    companion object {
        private const val LAUNCH_TIMEOUT = 5000L
    }

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        //Start
        val context: Context = ApplicationProvider.getApplicationContext()
        val intent = context.packageManager.getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT)
    }


    @Test
    fun checkFirstItemAndEnterDetail() {
        val name = "Starship-1"
        val item = findChildFromRecyclerView<TextView>("Starship-1")
        item.exists()
        item.click()
        device.waitForWindowUpdate(BASIC_SAMPLE_PACKAGE, 5000)
        device.findObject(UiSelector().text(name))
    }


}