package alexrnov.butterflies.testutils;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import androidx.appcompat.app.AppCompatActivity;

/** Compares TextView SP-values */
public class TestViewSPMatcher {

  private float density;

  public TestViewSPMatcher(AppCompatActivity activity) {
    DisplayMetrics dm = activity.getResources().getDisplayMetrics();
    density = dm.scaledDensity;
  }

  public TypeSafeMatcher<View> isSize(final float size) {
    return new TypeSafeMatcher<View>() {
      @Override
      protected boolean matchesSafely(View item) {
        float sp = ((TextView) item).getTextSize() / density;
        return sp == size;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Compares sp values");
      }
    };
  }
}
