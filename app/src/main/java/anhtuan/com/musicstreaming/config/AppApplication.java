package anhtuan.com.musicstreaming.config;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import anhtuan.com.musicstreaming.di.AppInjector;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

public class AppApplication extends Application implements HasActivityInjector, LifecycleObserver {
  @Inject
  DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;


  private static AppApplication application;

  public static AppApplication getInstance() {
    return application;
  }

  @Override
  public void onCreate() {
    super.onCreate();
//    Fabric.with(this, new Crashlytics());

    AppInjector.init(this);

    application = this;

    ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

  @OnLifecycleEvent(Event.ON_RESUME)
  public void onAppResume() {
    // when app open
  }

  @OnLifecycleEvent(Event.ON_PAUSE)
  public void onAppPause() {
    // when pause App
//    MainPreferences.increaseEnterApp();
  }

  @OnLifecycleEvent(Event.ON_DESTROY)
  public void onAppDestroy() {

  }
}

