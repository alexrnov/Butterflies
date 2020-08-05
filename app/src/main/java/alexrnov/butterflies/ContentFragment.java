package alexrnov.butterflies;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import alexrnov.butterflies.ButterfliesAdapter;
import alexrnov.butterflies.LoginViewModel;import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/** A content fragment containing a view */
public class ContentFragment extends Fragment {

  // Fields that need to be injected by the login graph
  @Inject LoginViewModel loginViewModel;

  private static final String ARG_SECTION_NUMBER = "section_number";

  //private PageViewModel pageViewModel;
  private RecyclerView recyclerView;
  private ButterfliesAdapter adapter;

  public static ContentFragment newInstance(int index) {
    ContentFragment fragment = new ContentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_SECTION_NUMBER, index);
    fragment.setArguments(bundle);
    return fragment;
  }

  public void onAttach(@NonNull Context context) {
    // When using fragments, inject Dagger in the fragment's onAttach() method.
    // In this case, it can be done before or after calling super.onAttach().
    super.onAttach(context);

    // Obtaining the login graph from LoginActivity and instantiate
    // the @Inject fields with objects from the graph
    ((MainActivity) getActivity()).loginComponent.inject(this);

    // Now you can access loginViewModel here and onCreateView too
    // (shared instance with the Activity and the other Fragment)

    Log.i("P", "loginViewModel.userRepository.s1 fragment = " + loginViewModel.getUserRepository().getLocalDataSource().getS1());
    Log.i("P", "loginViewModel.userRepository.s2 fragment = " + loginViewModel.getUserRepository().getRemoteDataSource().getS2());

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

    loginViewModel.setIndex(index);
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

    loginViewModel.getItems().observe(this, items -> {
      adapter = new ButterfliesAdapter(items);
      recyclerView.setAdapter(adapter);
    });

    return root;
  }
}