package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import anhtuan.com.musicstreaming.MainActivity;
import anhtuan.com.musicstreaming.R;
import anhtuan.com.musicstreaming.databinding.SplashFragmentBinding;
import anhtuan.com.musicstreaming.di.Injectable;
import anhtuan.com.musicstreaming.utils.AutoClearedValue;
import javax.inject.Inject;

public class SplashFragment extends BaseFragment implements Injectable {

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  AutoClearedValue<SplashFragmentBinding> dataBinding;

  SplashFragmentBinding binding;

  private SplashViewModel mViewModel;

  public static SplashFragment newInstance() {
    return new SplashFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil
        .inflate(inflater, R.layout.splash_fragment, container, false, dataBindingComponent);

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);

    gotoMainActivity();


  }

  private void gotoMainActivity() {
    startActivity(new Intent(getActivity(), MainActivity.class));
  }

}
