package anhtuan.com.musicstreaming.adapter;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder implements
    IItemTouchViewHolder {
  public final T binding;

  public BaseViewHolder(T binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  @Override
  public void onItemSelected() {
    binding.getRoot().setBackgroundColor(Color.LTGRAY);
  }

  @Override
  public void onItemClear() {
    binding.getRoot().setBackgroundColor(0);
  }
}


