package anhtuan.com.musicstreaming.fragment;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import anhtuan.com.musicstreaming.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import common.ITitle;
import common.NavigationController;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends BaseFragment implements LifecycleOwner, ITitle {

  @Inject
  NavigationController mNav;

  private static final String TITLE = "title";
  private static final String URL = "url";
  @BindView(R.id.webView)
  WebView webView;
  @BindView(R.id.loading_progress)
  ProgressBar loadingProgress;
  Unbinder unbinder;

  private String title;
  private String url;

  public static WebViewFragment newInstance(String title, String url) {
    WebViewFragment fragment = new WebViewFragment();

    Bundle args = new Bundle();
    args.putString(TITLE, title);
    args.putString(URL, url);

    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    Bundle args = getArguments();
    this.title = args.getString(TITLE);
    this.url = args.getString(URL);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_web_view, container, false);
    unbinder = ButterKnife.bind(this, view);

    mNav.managementToolbar(this);
    setHasOptionsMenu(true);

    webView.setWebViewClient(new WebViewClient());
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
    webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
    webView.setWebChromeClient(new WebChromeClient());
    webView.loadUrl(url);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    webView.loadUrl(this.url);

    loadingProgress.setVisibility(View.VISIBLE);

    webView.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (loadingProgress != null) {
          loadingProgress.setVisibility(View.GONE);
        }
      }

      @Override
      public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
        if (loadingProgress != null) {
          loadingProgress.setVisibility(View.GONE);
        }
      }
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public String getTitle() {
    return this.title;
  }
}

