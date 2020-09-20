package alexrnov.butterflies.testutils;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.core.content.ContextCompat;
import androidx.test.espresso.matcher.BoundedMatcher;

public class Utils {

  /** Allows comparison by text color */
  public static Matcher<View> withTextColor(final int expectedId) {
    return new BoundedMatcher<View, TextView>(TextView.class) {
      @Override
      protected boolean matchesSafely(TextView textView) {
        int colorId = ContextCompat.getColor(textView.getContext(), expectedId);
        return textView.getCurrentTextColor() == colorId;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("comparing text color");
        description.appendValue(expectedId);
      }
    };
  }

}
