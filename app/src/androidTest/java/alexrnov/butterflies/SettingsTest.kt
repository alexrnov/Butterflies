package alexrnov.butterflies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest // Marks a test that should run as part of the large tests.
class SettingsTest {

  @get:Rule
  var activityRule: ActivityTestRule<MainActivity>
          = ActivityTestRule(MainActivity::class.java)

  @Test
  fun openAllItemsFromFirstTab() {
    for (i in 0..7) {
      onView(Matchers.allOf(isDisplayed(), withId(R.id.items_recycler_view)))
              .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click()))

      onView(withId(R.id.big_image)).check(matches(isDisplayed()))
      onView(withId(R.id.description_text)).check(matches(isDisplayed()))
      onView(withId(R.id.detailsAppToolbar)).check(matches(isDisplayed()))

      onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    }
  }
}