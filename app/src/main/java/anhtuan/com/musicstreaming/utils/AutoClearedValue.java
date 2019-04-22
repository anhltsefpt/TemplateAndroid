package anhtuan.com.musicstreaming.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class AutoClearedValue<T> {
  /** The observatory value use for clear. */
  private T value;

  /**
   * The constructor.
   *
   * @param fragment Tracking fragment
   * @param value    Observatory value
   */
  public AutoClearedValue(final Fragment fragment, final T value) {
    final FragmentManager fragmentManager = fragment.getFragmentManager();
    if (fragmentManager != null) {
      fragmentManager.registerFragmentLifecycleCallbacks(
          new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewDestroyed(final FragmentManager fm, final Fragment f) {
              if (f == fragment) {
                AutoClearedValue.this.value = null;
                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
              }
            }
          }, false);
    }

    this.value = value;
  }

  /**
   * Get the observatory value.
   *
   * @return The observatory value
   */
  public T get() {
    return value;
  }
}


