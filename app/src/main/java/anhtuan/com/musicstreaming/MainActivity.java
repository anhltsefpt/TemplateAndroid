package anhtuan.com.musicstreaming;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import anhtuan.com.musicstreaming.activity.BaseActivity;
import common.NavigationController;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import java.util.HashMap;
import java.util.Stack;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector,
    OnTabSelectedListener{

  @Inject
  NavigationController mNavigationController;

  public static final int MY_LIBRARY = 2;
  public static final int PLAYLIST = 1;
  public static final int SEARCH = 0;
  public static final int MORE = 3;

  Menu mMenu;

  private HashMap<Integer, Stack<String>> mBackStacks = new HashMap<>();
  private TabLayout mTabLayout;

  public static final String BUNDLE_TAB_BACK_STACK = "tab_back_stacks";
  public static final String BUNDLE_TAB_POSITION = "tab_position";

  public static final int DEFAULT_TAB_POSITION = 0;

  @Inject
  DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      mBackStacks.put(MY_LIBRARY, new Stack<>());
      mBackStacks.put(PLAYLIST, new Stack<>());
      mBackStacks.put(SEARCH, new Stack<>());
      mBackStacks.put(MORE, new Stack<>());

      mNavigationController.setupDefaultToolbar();
      // Setup the default tool bar
//      mNavigationController.setupDefaultToolbar();s
    } else {
      mBackStacks = (HashMap<Integer, Stack<String>>) savedInstanceState
          .getSerializable(BUNDLE_TAB_BACK_STACK);
    }

    mTabLayout = findViewById(R.id.tab_layout);
    mTabLayout.addOnTabSelectedListener(this);

    mNavigationController.initTab(mTabLayout);
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }

  public Menu getmMenu() {
    return mMenu;
  }

  @Nullable
  public Fragment getTopFragment() {
    Stack<String> backStack = getCurrentBackStack();
    if (backStack.isEmpty()) {
      return null;
    }
    String tag = backStack.peek();
    FragmentManager fragmentManager = getSupportFragmentManager();
    return fragmentManager.findFragmentByTag(tag);
  }

  public HashMap<Integer, Stack<String>> getmBackStacks() {
    return mBackStacks;
  }

  public void setmBackStacks(
      HashMap<Integer, Stack<String>> mBackStacks) {
    this.mBackStacks = mBackStacks;
  }

  @Override
  public void onBackPressed() {
    if (!mNavigationController.backPress()) {
      super.onBackPressed();
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(BUNDLE_TAB_POSITION, mTabLayout.getSelectedTabPosition());
    outState.putSerializable(BUNDLE_TAB_BACK_STACK, mBackStacks);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    int saved = savedInstanceState.getInt(BUNDLE_TAB_POSITION, DEFAULT_TAB_POSITION);
    Tab tab = mTabLayout.getTabAt(saved);
    if (tab != null) {
      tab.select();
    }
  }

  public Stack<String> getCurrentBackStack() {
    int pos = mTabLayout.getSelectedTabPosition();
    return getStack(pos);
  }

  public Stack<String> getStack(final int position) {
    try {
      return mBackStacks.get(position);
    } catch (Exception e) {
      return new Stack<>();
    }

  }

  @Override
  public void onTabSelected(Tab tab) {
    mNavigationController.tabSelected(tab);
  }

  @Override
  public void onTabUnselected(Tab tab) {
    mNavigationController.tabUnselected(tab);
  }

  @Override
  public void onTabReselected(Tab tab) {
    mNavigationController.tabReselected(tab);
  }
}
