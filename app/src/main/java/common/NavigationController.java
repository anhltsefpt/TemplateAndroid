package common;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import anhtuan.com.musicstreaming.MainActivity;
import anhtuan.com.musicstreaming.R;
import anhtuan.com.musicstreaming.config.AppApplication;
import anhtuan.com.musicstreaming.fragment.MoreFragment;
import anhtuan.com.musicstreaming.fragment.MyLibraryFragment;
import anhtuan.com.musicstreaming.fragment.PlayListFragment;
import anhtuan.com.musicstreaming.fragment.SearchFragment;
import anhtuan.com.musicstreaming.fragment.WebViewFragment;
import java.util.Stack;
import java.util.UUID;
import javax.inject.Inject;
import widget.CenteredToolbar;
import widget.SearchBarView;
import widget.SearchBarView.OnSearchBarListener;

public class NavigationController {
  private final FragmentManager fragmentManager;
  private final MainActivity mActivity;
  private final int containerId;

  @Inject
  public NavigationController(MainActivity mainActivity) {
    this.fragmentManager = mainActivity.getSupportFragmentManager();
    this.mActivity = mainActivity;
    this.containerId = R.id.container;
  }

  public void addFragment(Fragment fragment) {
    addFragment(fragment, mActivity.getCurrentBackStack());
  }

  private void addFragment(Fragment fragment, Stack<String> backStack) {
    String tag = UUID.randomUUID().toString();
    fragmentManager.beginTransaction()
        .add(containerId, fragment, tag)
        .commitAllowingStateLoss();
    backStack.push(tag);

    if (fragment instanceof WebViewFragment) {
      mActivity.findViewById(R.id.tab_layout).setVisibility(View.GONE);
    } else {
      mActivity.findViewById(R.id.tab_layout).setVisibility(View.VISIBLE);
    }
//    if (fragment instanceof PremiumFragment || MainPreferences.getIsPremiumUser()
//        || fragment instanceof NewsFullFragment) {
//      mActivity.findViewById(R.id.buyPremium).setVisibility(View.GONE);
//    } else {
//      mActivity.findViewById(R.id.buyPremium).setVisibility(View.VISIBLE);
//    }
  }

  public void showTitle(String title) {
    CenteredToolbar centeredToolbar = mActivity.findViewById(R.id.toolbar);
    centeredToolbar.setVisibility(View.VISIBLE);

    mActivity.findViewById(R.id.search_bar).setVisibility(View.GONE);

    centeredToolbar.showTitle(title);
  }

  public void showSearchBar(SearchBarView.OnSearchBarListener listener, boolean focus) {
    SearchBarView searchBarView = mActivity.findViewById(R.id.search_bar);
    searchBarView.setSearchBarListener(listener);
    searchBarView.show(focus);
    searchBarView.setVisibility(View.VISIBLE);

    mActivity.findViewById(R.id.toolbar).setVisibility(View.GONE);
  }

  public void hiddenKeyboard() {
    try {
      SearchBarView searchBarView = mActivity.findViewById(R.id.search_bar);
      searchBarView.showKeyboard(false);
    } catch (Exception e) {

    }
  }


  public void hideSearchBar() {
    try {
      SearchBarView searchBarView = mActivity.findViewById(R.id.search_bar);
      searchBarView.hide();
    } catch (Exception e) {

    }
  }

  private void addTab(TabLayout tabLayout, int textId, int iconId, boolean isSelect) {
    TabLayout.Tab watchList = tabLayout.newTab();
    watchList.setText(AppApplication.getInstance().getString(textId));
    watchList.setIcon(iconId);
    tabLayout.addTab(watchList, isSelect);
  }

  public boolean backPress() {
    Stack<String> backStack = mActivity.getCurrentBackStack();

    if (backStack.isEmpty()) {
      return true;
    }

    String tag = backStack.pop();
    Fragment fragment = fragmentManager.findFragmentByTag(tag);
    if ((fragment instanceof SearchBarView.OnSearchBarListener)) {
      if (mActivity.findViewById(R.id.search_bar).getVisibility() == View.VISIBLE
          && ((SearchBarView.OnSearchBarListener) fragment).backedNecessary()) {
        ((SearchBarView) mActivity.findViewById(R.id.search_bar)).clearText();
        backStack.push(tag);
        return true;
      } else {

      }
//      if (fragment instanceof WatchTopFragment) {
//        ((WatchTopFragment) fragment).onReloadPremium();
//      }
//    } else if (fragment instanceof MoreFragment) {
//      backStack.push(tag);
//      ((MoreFragment) fragment).onReload();
//      return true;
//    } else if (fragment instanceof ScreenerBuilderFragment) {
//      backStack.push(tag);
//      return true;
//    } else if (fragment instanceof NewsBlogFragment) {
//      backStack.push(tag);
//      return true;
//    } else if (fragment instanceof ScreenerBuilder2Fragment) {
//      backStack.push(tag);
//      return true;
//    }

      if (backStack.isEmpty()) {
        return true;
      }

      fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
      showFragment(backStack);

      tag = backStack.peek();
      fragment = fragmentManager.findFragmentByTag(tag);

      managementToolbar(fragment);
//
//      if (fragment instanceof WatchTopFragment) {
//        ((WatchTopFragment) fragment).refresher();
//      }
//
//      if (fragment instanceof ListCoinFragment) {
//        updateStatusFavorite(((ListCoinFragment) fragment).isSaved());
//      }
//
//      if (fragment instanceof StockDetailFragment) {
//        ((StockDetailFragment) fragment).updateToolbar();
//      }
//
//      if (fragment instanceof AlertFragment) {
//        ((AlertFragment) fragment).refreshData();
//      }
//
//      if (fragment instanceof MoreFragment) {
//        ((MoreFragment) fragment).onReload();
//      }

      if (fragment instanceof WebViewFragment) {
        mActivity.findViewById(R.id.tab_layout).setVisibility(View.GONE);
      } else {
        mActivity.findViewById(R.id.tab_layout).setVisibility(View.VISIBLE);
      }
//    if (fragment instanceof PremiumFragment || MainPreferences.getIsPremiumUser()
//        || fragment instanceof NewsFullFragment) {
//      mActivity.findViewById(R.id.buyPremium).setVisibility(View.GONE);
//    } else {
//      mActivity.findViewById(R.id.buyPremium).setVisibility(View.VISIBLE);
//    }

      return true;
    }

    return true;
  }

    public void manageToolbar(String title) {
      CenteredToolbar centeredToolbar = mActivity.findViewById(R.id.toolbar);
      centeredToolbar.setVisibility(View.VISIBLE);

      mActivity.findViewById(R.id.search_bar).setVisibility(View.GONE);

      centeredToolbar.showTitle(title);
    }


  public void setupDefaultToolbar() {
    Toolbar toolbar = mActivity.findViewById(R.id.toolbar);
    mActivity.setSupportActionBar(toolbar);
    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
    params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
    toolbar.setLayoutParams(params);
  }

  public void initTab(TabLayout tabLayout) {
    addTab(tabLayout, R.string.tab_my_library, R.drawable.selector_tab_watchlist, true);
    addTab(tabLayout, R.string.tab_my_playlist, R.drawable.selector_tab_watchlist, false);
    addTab(tabLayout, R.string.tab_search, R.drawable.selector_tab_watchlist, false);
    addTab(tabLayout, R.string.tab_more, R.drawable.selector_tab_watchlist, false);
  }

  public void tabSelected(TabLayout.Tab tab) {
    int position = tab.getPosition();
//    if (position == MainActivity.DEFAULT_TAB_POSITION) {
//      UtilsAnalytic.getInstance().postTabWatchList();
//    } else if (position == MainActivity.SCREENER_BUILDER){
//      UtilsAnalytic.getInstance().postTabScreener();
//    } else if (position == MainActivity.ALERT) {
//      UtilsAnalytic.getInstance().postTabAlert();
//    } else if (position == MainActivity.NEWS) {
//      UtilsAnalytic.getInstance().postNews();
//    }

    Stack<String> backStack = mActivity.getStack(position);
    if (backStack.isEmpty()) {
      String fragmentName;
      switch (position) {
        case MainActivity.PLAYLIST:
          fragmentName = PlayListFragment.class.getName();
          break;
        case MainActivity.MY_LIBRARY:
          fragmentName = MyLibraryFragment.class.getName();
          break;
        case MainActivity.MORE:
          fragmentName = MoreFragment.class.getName();
          break;
        case MainActivity.SEARCH:
          fragmentName = SearchFragment.class.getName();
          break;
        default:
          throw new RuntimeException("this Tab not support");
      }
      Fragment fragment = Fragment.instantiate(mActivity, fragmentName);
      addFragment(fragment, backStack);
    } else {
      showFragment(backStack);
    }
  }

  public void tabUnselected(TabLayout.Tab tab) {
    Stack<String> backStack = mActivity.getStack(tab.getPosition());
    if (backStack.isEmpty()) {
      return;
    }

    int size = backStack.size();

    String[] tmp = new String[backStack.size()];
    backStack.copyInto(tmp);
    while (true) {
      String tag = tmp[size - 1];
      Fragment fragment = fragmentManager.findFragmentByTag(tag);
      fragmentManager.beginTransaction().detach(fragment).commitAllowingStateLoss();

      size -= 1;
      if (size <= 0) {
        break;
      }
    }
  }

  public void tabReselected(TabLayout.Tab tab) {
    Stack<String> backStack = mActivity.getStack(tab.getPosition());
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    int position = tab.getPosition();
    if (position == MainActivity.MY_LIBRARY) {
      if (backStack.size() > 1) {
        mActivity.onBackPressed();
        return;
      } else {
        return;
      }
    } else if (position == MainActivity.PLAYLIST) {
      if (backStack.size() > 1) {
        mActivity.onBackPressed();
        return;
      } else {
        return;
      }
    } else if (position == MainActivity.MORE) {
      if (backStack.size() > 1) {
        mActivity.onBackPressed();
        return;
      } else {
        return;
      }
    } else if (position == MainActivity.SEARCH) {
      if (backStack.size() > 1) {
        mActivity.onBackPressed();
        return;
      } else {
        return;
      }
    }

    while (backStack.size() > 1) {
      String tag = backStack.pop();
      Fragment fragment = fragmentManager.findFragmentByTag(tag);
      fragmentTransaction.remove(fragment);
    }
    if (backStack.isEmpty()) {
      return;
    }

    Fragment fragment = fragmentManager.findFragmentByTag(backStack.peek());
    managementToolbar(fragment);

    fragmentTransaction.commitAllowingStateLoss();
    showFragment(backStack);
  }


  private void showFragment(Stack<String> backStack) {
    if (backStack.isEmpty()) {
      return;
    }
    String tag = backStack.peek();
    Fragment fragment = fragmentManager.findFragmentByTag(tag);
    fragmentManager.beginTransaction()
        .attach(fragment)
        .commitAllowingStateLoss();
  }

  public void managementToolbar(Fragment fragment) {
    Fragment fragmentTop = mActivity.getTopFragment();
    if (fragment != fragmentTop) {
      return;
    }
    if (fragment instanceof SearchBarView.OnSearchBarListener) {
      showSearchBar(fragment);
    } else if (fragment instanceof ITitle){
      showTitleToolbar(((ITitle) fragment).getTitle());
    }
  }


  private void showSearchBar(Fragment fragment) {
    showSearchBar((OnSearchBarListener) fragment, false);
  }

  private void  showTitleToolbar(String title) {
    showTitle(title);
  }
}
