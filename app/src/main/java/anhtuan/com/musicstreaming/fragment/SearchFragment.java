package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import anhtuan.com.musicstreaming.R;
import anhtuan.com.musicstreaming.di.Injectable;
import javax.inject.Inject;

public class SearchFragment extends BaseFragment implements Injectable {

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  private SearchViewModel mViewModel;

  public static SearchFragment newInstance() {
    return new SearchFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.search_fragment, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
    // TODO: Use the ViewModel
  }

}
