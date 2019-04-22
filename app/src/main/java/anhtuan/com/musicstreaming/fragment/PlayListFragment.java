package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import anhtuan.com.musicstreaming.R;
import anhtuan.com.musicstreaming.di.Injectable;
import javax.inject.Inject;

public class PlayListFragment extends BaseFragment implements Injectable {

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  private PlayListViewModel mViewModel;

  public static PlayListFragment newInstance() {
    return new PlayListFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.play_list_fragment, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayListViewModel.class);
    // TODO: Use the ViewModel
  }

}
