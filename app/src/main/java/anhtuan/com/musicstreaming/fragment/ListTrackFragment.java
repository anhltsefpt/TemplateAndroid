package anhtuan.com.musicstreaming.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import anhtuan.com.musicstreaming.R;
import anhtuan.com.musicstreaming.databinding.FragmentRecycleBinding;
import anhtuan.com.musicstreaming.di.Injectable;
import anhtuan.com.musicstreaming.utils.AutoClearedValue;
import anhtuan.com.musicstreaming.viewmodel.ListTrackViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListTrackFragment extends BaseFragment implements Injectable {

  FragmentRecycleBinding binding;
  AutoClearedValue<FragmentRecycleBinding> databinding;

  ListTrackViewModel viewModel;

  public ListTrackFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycle, container, false, dataBindingComponent);

    databinding = new AutoClearedValue<>(this, binding);

    return databinding.get().getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ListTrackViewModel.class);
  }
}
