package alexrnov.butterflies.pager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import alexrnov.butterflies.MainActivity;
import alexrnov.butterflies.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import kotlin.Pair;

import static alexrnov.butterflies.ApplicationUtilsKt.getScreenSizeWithNavBar;

/** A content fragment containing a view fo current page */
public class PageContentFragment extends Fragment {

  // fields that need to be injected by the login graph
  @Inject
  PageViewModel pageViewModel;

  private static final String ARG_SECTION_NUMBER = "section_number";

  private RecyclerView recyclerView;
  private ButterfliesAdapter adapter;

  private Boolean landscape = false;

  public static PageContentFragment newInstance(int index) {
    PageContentFragment fragment = new PageContentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_SECTION_NUMBER, index);
    fragment.setArguments(bundle);
    return fragment;
  }

  // when using fragments, inject Dagger in the fragment's onAttach() method.
  // In this case, it can be done before or after calling super.onAttach().
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);

    // obtaining the activity graph from MainActivity and instantiate
    // the @Inject fields with objects from the graph
    MainActivity mainActivity = (MainActivity) getActivity();
    if (mainActivity != null) {
      mainActivity.activityComponent.inject(this);
    }
    // now access PageViewModel here and onCreateView too
    // (shared instance with the Activity and the other Fragment)
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Context context = getContext();
    if (context != null) landscape = context.getResources().getBoolean(R.bool.is_landscape);

    //pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class); // is deprecated
    //pageViewModel = new ViewModelProvider(this).get(PageViewModel.class); // in case, when di don't use
    int index = 1;
    if (getArguments() != null) {
      index = getArguments().getInt(ARG_SECTION_NUMBER);
    }
    pageViewModel.setIndex(index);
  }

  @Override
  public View onCreateView(
          @NonNull LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_main, container, false);

    recyclerView = root.findViewById(R.id.recycler_view);
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView.setHasFixedSize(true);

    LayoutManager layoutManager;
    if (landscape) {
      // GridLayoutManager arranges the items in a many-dimensional list
      layoutManager = new GridLayoutManager(getActivity(), getGridNumber());
    } else {
      layoutManager = new LinearLayoutManager(getActivity());
    }
    recyclerView.setLayoutManager(layoutManager);

    pageViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
      adapter = new ButterfliesAdapter(items, landscape);
      recyclerView.setAdapter(adapter);
    });

    return root;
  }

  private int getGridNumber() {
    Pair<Float, Float> sizes = getScreenSizeWithNavBar(requireActivity());
    Log.i("P", "screen size = " + sizes.getFirst() + ", " + sizes.getSecond());
    Float w = sizes.getSecond();
    int gridNumber;
    if (w < 530.0) { // 3.2 HVGA slider (ADP1) Api 23 (320 x 480 mdpi)
      gridNumber = 2;
    } else if (w >= 530 && w < 800) { //  nexus one api 26 (480 x 800 hdpi)
      gridNumber = 3;
    } else if (w >= 800 && w < 1024) { // sony xperia z ultra and samsung galaxy
      gridNumber = 4;
    } else if (w >= 1024 && w < 1280) { // Nexus 9 api 26 (2048 x 1536 xhdpi)
      gridNumber = 5;
    } else { // Nexus 10 Api 30 (2560 x 1600 xhdpi)
      gridNumber = 6;
    }
    return gridNumber;
  }
}