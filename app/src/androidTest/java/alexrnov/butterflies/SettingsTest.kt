package alexrnov.butterflies

import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import alexrnov.butterflies.testutils.TestViewSPMatcher
import alexrnov.butterflies.testutils.Utils.withTextColor
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matcher
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
  fun changeFontSize() {
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    onView(Matchers.allOf(isDisplayed(), withId(R.id.items_recycler_view)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

    onView(withId(R.id.big_image)).check(ViewAssertions.matches(isDisplayed()))
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isDisplayed()))
    onView(withId(R.id.detailsAppToolbar)).check(ViewAssertions.matches(isDisplayed()))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.very_min_size)).perform(click())
    // check the appearance of the snackbar
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    // press up home button
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

    onView(withId(R.id.big_image)).check(ViewAssertions.matches(isDisplayed()))
    onView(withId(R.id.detailsAppToolbar)).check(ViewAssertions.matches(isDisplayed()))
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isDisplayed()))
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isSP(12.0f)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.min_size)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isSP(16.0f)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.middle_size)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isSP(18.0f)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.big_size)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isSP(22.0f)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.very_big_size)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(isSP(26.0f)))
  }

  @Test
  fun changeFontColor() {
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    onView(Matchers.allOf(isDisplayed(), withId(R.id.items_recycler_view)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_color)).perform(click())
    onView(withText(R.string.gray_color)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    // press up home button
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(withTextColor(R.color.GrayTextColor)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_color)).perform(click())
    onView(withText(R.string.black_color)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(withTextColor(R.color.BlackTextColor)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_color)).perform(click())
    onView(withText(R.string.brown_color)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(withTextColor(R.color.BrownTextColor)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_color)).perform(click())
    onView(withText(R.string.blue_color)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(withTextColor(R.color.BlueTextColor)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_color)).perform(click())
    onView(withText(R.string.green_color)).perform(click())
    onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.description_text)).check(ViewAssertions.matches(withTextColor(R.color.GreenTextColor)))
  }

  @Test
  fun aboutApp() {
    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.program_information)).perform(click())
    onView(withText(R.string.version_pref)).check(ViewAssertions.matches(isDisplayed()))
    onView(withText(R.string.additional_resources)).check(ViewAssertions.matches(isDisplayed()))
    // press up home button
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withText(R.string.about_program_category)).check(ViewAssertions.matches(isDisplayed()))
  }

  private fun isSP(size: Float): Matcher<View> {
    return TestViewSPMatcher(activityRule.activity).isSize(size)
  }
}