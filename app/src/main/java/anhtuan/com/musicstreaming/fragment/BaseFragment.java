package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import anhtuan.com.musicstreaming.R;
import anhtuan.com.musicstreaming.binding.FragmentDataBindingComponent;
import anhtuan.com.musicstreaming.di.Injectable;
import anhtuan.com.musicstreaming.databinding.FragmentRecycleBinding;

import anhtuan.com.musicstreaming.entity.Status;
import anhtuan.com.musicstreaming.utils.AutoClearedValue;
import javax.inject.Inject;

public class BaseFragment extends Fragment implements Injectable {
  @Inject
  public ViewModelProvider.Factory viewModelFactory;

  android.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

  AutoClearedValue<FragmentRecycleBinding> binding;

  public BaseFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    TextView textView = new TextView(getActivity());
    return textView;
  }

  protected FragmentRecycleBinding createDataBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
    FragmentRecycleBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycle,
        container, false, dataBindingComponent);
    dataBinding.setStatus(Status.LOADING);
    return dataBinding;
  }

}

