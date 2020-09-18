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

private const val LAUNCH_TIMEOUT = 1000L
private const val BASIC_SAMPLE_PACKAGE = "alexrnov.butterflies"

@RunWith(AndroidJUnit4::class)
// the annotation verifies that the API is at least version 18, as required by the Automator framework
@SdkSuppress(minSdkVersion = 18)
class PagerTest {
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
            LAUNCH_TIMEOUT ) // Wait
  }

  @Test
  fun recycler_scroll() {
    InstrumentationRegistry.getInstrumentation().uiAutomation
            .setRotation(Surface.ROTATION_0) // portrait orientation
    val list = UiScrollable(UiSelector().resourceId("$BASIC_SAMPLE_PACKAGE:id/recycler_view"))
    list.flingToEnd(7)
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT ) // Wait
  }
}