package alexrnov.butterflies

import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * FragmentScenario also supports testing of dialogs. Even though dialogs are instances
 * of graphical fragments, you use the launchFragment() method so that the dialog's elements
 * are populated in the dialog itself, rather than in the activity that launches the dialog.
 */
@RunWith(AndroidJUnit4::class)
class AboutDialogTest {

  private lateinit var buttonLabel: String

  @get:Rule
  var activityRule: ActivityTestRule<MainActivity>
          = ActivityTestRule(MainActivity::class.java)

  @Before
  fun initString() {
    buttonLabel = activityRule.activity.getString(R.string.ok_button)
  }

  @Test
  fun showDialog() {
    val scenario = launchFragment<AboutDialogFragment>()
    scenario.onFragment { fragment ->
      assertThat(fragment.dialog).isNotNull()
      assertThat(fragment.requireDialog().isShowing).isTrue()
    }
    onView(withId(R.id.close_dialog_button)).check(matches(isDisplayed()))
    onView(withId(R.id.close_dialog_button)).check(matches(ViewMatchers.withText(buttonLabel)))

    scenario.onFragment { fragment ->
      fragment.dismiss()
      // is deprecated
      //fragment.requireFragmentManager().executePendingTransactions()
      fragment.parentFragmentManager.executePendingTransactions()
      assertThat(fragment.dialog).isNull()
    }
    onView(ViewMatchers.withText(buttonLabel)).check(ViewAssertions.doesNotExist())
  }

}