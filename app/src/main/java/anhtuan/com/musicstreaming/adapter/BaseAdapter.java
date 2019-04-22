package anhtuan.com.musicstreaming.adapter;

import android.annotation.SuppressLint;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class BaseAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<V>> {
  @Nullable
  private List<T> items;
  // Moi lan update se thi version se tang.
  // Cai nay dung de check xem data co phai la data moi hay cu.
  private int dataVersion = 0;

  protected abstract void bind(V binding, T item);

  protected abstract boolean areItemsTheSame(T oldItem, T newItem);

  protected abstract boolean areContentsTheSame(T oldItem, T newItem);

  protected abstract V createBinding(ViewGroup parent, int viewType);

  @Override
  public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override
  public final BaseViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
    V binding = createBinding(parent, viewType);
    return new BaseViewHolder<>(binding);
  }

  @Override
  public final void onBindViewHolder(BaseViewHolder<V> holder, int position) {
    bind(holder.binding, items.get(position));
    holder.binding.executePendingBindings();
    bindViewHolder(holder);
  }

  protected void bindViewHolder(BaseViewHolder<V> holder) {
  }

  @Nullable
  T getItem(int position) {
    if (items == null || position < 0 || position >= items.size()) {
      return null;
    }
    return items.get(position);
  }

  @SuppressLint("StaticFieldLeak")
  @MainThread
  public void replace(List<T> newData) {
    dataVersion++;
    if (items == null) {
      if (newData == null) {
        return;
      }
      items = newData;
      notifyDataSetChanged();
    } else if (newData == null) {
      int oldSize = items.size();
      items = null;
      notifyItemRangeRemoved(0, oldSize);
    } else {
      final int startVersion = dataVersion;
      final List<T> oldItems = items;
      new AsyncTask<Void, Void, DiffResult>() {
        @Override
        protected DiffUtil.DiffResult doInBackground(Void... voids) {
          return DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
              return oldItems.size();
            }

            @Override
            public int getNewListSize() {
              return newData.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
              T oldItem = oldItems.get(oldItemPosition);
              T newItem = newData.get(newItemPosition);
              return BaseAdapter.this.areItemsTheSame(oldItem, newItem);
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
              T oldItem = oldItems.get(oldItemPosition);
              T newItem = newData.get(newItemPosition);
              return BaseAdapter.this.areContentsTheSame(oldItem, newItem);
            }
          });
        }

        @Override
        protected void onPostExecute(DiffUtil.DiffResult diffResult) {
          if (startVersion != dataVersion) {
            // ignore newData
            return;
          }
          items = newData;
          diffResult.dispatchUpdatesTo(BaseAdapter.this);
        }
      }.execute();
    }
  }

  @Nullable
  public List<T> getDataList() {
    return items;
  }

  public void sort(Comparator<T> comparator) {
    if (items == null) {
      return;
    }

    Collections.sort(items, comparator);

    notifyDataSetChanged();
  }


  @MainThread
  public void deleteItem(T item) {
    if (items == null) {
      return;
    }
    items.remove(item);
    notifyDataSetChanged();
  }
}
