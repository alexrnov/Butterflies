package alexrnov.butterflies

import android.content.Context
import android.content.Intent
import android.view.Surface
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 5000L
private const val BASIC_SAMPLE_PACKAGE = "alexrnov.butterflies"

@RunWith(AndroidJUnit4::class)
// the annotation verifies that the API is at least version 18, as required by the Automator framework
@SdkSuppress(minSdkVersion = 18)
class MapTest {
  private lateinit var device: UiDevice

  @Before
  fun startMainActivityFromHomeScreen() {
    // Initialize UiDevice instance
    device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    device.pressHome() // Start from the home screen

    // wait run
    val launcherPackage: String = device.launcherPackageName
    ViewMatchers.assertThat(launcherPackage, CoreMatchers.notNullValue())
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT)

    // Launch the app
    val context = ApplicationProvider.getApplicationContext<Context>()
    val intent = context.packageManager
            .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)?.apply {
              // Clear out any previous instances
              addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

    context.startActivity(intent)
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT) // Wait
  }

  @Test
  fun markTest() {
    InstrumentationRegistry.getInstrumentation().uiAutomation
            .setRotation(Surface.ROTATION_90) // landscape orientation

    // Get the button object. If you want to access a specific UI component in your
    // application, use the UiSelector class. This class represents a request for
    // specific items in the currently displayed user interface.
    val mapButton: UiObject = device.findObject(UiSelector()
            .resourceId("$BASIC_SAMPLE_PACKAGE:id/action_map"))
    mapButton.click()

    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT) // Wait
    var marker = device.findObject(UiSelector().descriptionContains("cynorta"))
    marker.click()
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT) // Wait

    marker = device.findObject(UiSelector().descriptionContains("gallienus"))
    marker.click()
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT) // Wait

    marker = device.findObject(UiSelector().descriptionContains("antimachus"))
    marker.click()
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT) // Wait

    marker = device.findObject(UiSelector().descriptionContains("ophidicephalus"))
    marker.click()
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT) // Wait
  }
}