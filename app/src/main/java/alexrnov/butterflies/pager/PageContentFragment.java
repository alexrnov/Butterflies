package alexrnov.butterflies.pager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import alexrnov.butterflies.MainActivity;
import alexrnov.butterflies.R;
import alexrnov.butterflies.model.PageViewModel;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/** A content fragment containing a view */
public class PageContentFragment extends Fragment {

  // Fields that need to be injected by the login graph
  @Inject
  PageViewModel pageViewModel;

  private static final String ARG_SECTION_NUMBER = "section_number";

  //private PageViewModel pageViewModel;
  private RecyclerView recyclerView;
  private ButterfliesAdapter adapter;

  public static PageContentFragment newInstance(int index) {
    PageContentFragment fragment = new PageContentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_SECTION_NUMBER, index);
    fragment.setArguments(bundle);
    return fragment;
  }

  // When using fragments, inject Dagger in the fragment's onAttach() method.
  // In this case, it can be done before or after calling super.onAttach().
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);

    // Obtaining the login graph from LoginActivity and instantiate
    // the @Inject fields with objects from the graph
    ((MainActivity) getActivity()).activityComponent.inject(this);

    // Now you can access loginViewModel here and onCreateView too
    // (shared instance with the Activity and the other Fragment)

    //Log.i("P", "loginViewModel.userRepository.s1 fragment = " + loginViewModel.getUserRepository().getLocalDataSource().getS1());
    //Log.i("P", "loginViewModel.userRepository.s2 fragment = " + loginViewModel.getUserRepository().getRemoteDataSource().getS2());

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class); // is deprecated
    //pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
    int index = 1;
    if (getArguments() != null) {
      index = getArguments().getInt(ARG_SECTION_NUMBER);
    }
    //pageViewModel.setIndex(index);

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
    // GridLayoutManager arranges the items in a many-dimensional list
    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

    pageViewModel.getItems().observe(this, items -> {
      adapter = new ButterfliesAdapter(items);
      recyclerView.setAdapter(adapter);
    });

    return root;
  }
}