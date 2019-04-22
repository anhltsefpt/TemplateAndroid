package anhtuan.com.musicstreaming.binding;

import android.support.v4.app.Fragment;

public class FragmentDataBindingComponent implements android.databinding.DataBindingComponent {
  private final FragmentBindingAdapter adapter;

  public FragmentDataBindingComponent(final Fragment fragment) {
    this.adapter = new FragmentBindingAdapter(fragment);
  }

  @Override
  public FragmentBindingAdapter getFragmentBindingAdapter() {
    return adapter;
  }
}

