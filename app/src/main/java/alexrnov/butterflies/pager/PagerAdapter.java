package alexrnov.butterflies.pager;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import alexrnov.butterflies.R;
import alexrnov.butterflies.pager.PageContentFragment;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PagerAdapter extends FragmentPagerAdapter {

  @StringRes
  private static final int[] TAB_TITLES = new int[] {R.string.tab1, R.string.tab2,
          R.string.tab3, R.string.tab4, R.string.tab5, R.string.tab6, R.string.tab7};

  private final Context context;

  public PagerAdapter(Context context, FragmentManager fm) {
    super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    this.context = context;
  }

  @NotNull
  @Override
  public Fragment getItem(int position) {
    // getItem is called to instantiate the fragment for the given page.
    // Return a PageContentFragment (defined as a static inner class below).
    return PageContentFragment.newInstance(position);
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return context.getResources().getString(TAB_TITLES[position]);
  }

  @Override
  public int getCount() {
    return TAB_TITLES.length; // tabs amount
  }
}