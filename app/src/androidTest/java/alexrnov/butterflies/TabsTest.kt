package alexrnov.butterflies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest // Marks a test that should run as part of the medium tests.
class TabsTest {

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

  @Test
  fun openAllTabs() {
    (1..6).forEach { tab ->
      onView(withId(R.id.tabs)).perform(selectTabAtPosition(tab))
    }
    (6 downTo 0).forEach { tab ->
      onView(withId(R.id.tabs)).perform(selectTabAtPosition(tab))
    }
  }

  private fun selectTabAtPosition(tabIndex: Int): ViewAction {
    return object : ViewAction {
      override fun getDescription() = "with tab at index $tabIndex"

      override fun getConstraints() = Matchers.allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

      override fun perform(uiController: UiController, view: View) {
        val tabLayout = view as TabLayout
        val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

        tabAtIndex.select()
      }
    }
  }
}