package anhtuan.com.musicstreaming.di;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import anhtuan.com.musicstreaming.config.AppApplication;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class AppInjector {
  private AppInjector(){}
  public static void init(AppApplication appApplication) {
    DaggerAppComponent.builder()
        .application(appApplication)
        .build()
        .inject(appApplication);

    appApplication.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
      @Override
      public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        handleActivity(activity);
      }

      @Override
      public void onActivityStarted(Activity activity) {

      }

      @Override
      public void onActivityResumed(Activity activity) {

      }

      @Override
      public void onActivityPaused(Activity activity) {

      }

      @Override
      public void onActivityStopped(Activity activity) {

      }

      @Override
      public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

      }

      @Override
      public void onActivityDestroyed(Activity activity) {

      }
    });
  }

  private static void handleActivity(Activity activity) {
    if (activity instanceof HasSupportFragmentInjector) {
      AndroidInjection.inject(activity);
    }

    if (activity instanceof FragmentActivity) {
      ((FragmentActivity) activity).getSupportFragmentManager()
          .registerFragmentLifecycleCallbacks(
              new FragmentLifecycleCallbacks() {
                @Override
                public void onFragmentCreated(FragmentManager fm, Fragment f,
                    Bundle savedInstanceState) {
                  if (f instanceof Injectable) {
                    AndroidSupportInjection.inject(f);
                  }
                }
              }, true
          );
    }
  }
}

