package anhtuan.com.musicstreaming.di;

import anhtuan.com.musicstreaming.activity.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashModule {
  @ContributesAndroidInjector(modules = SplashFragmentModule.class)
  abstract SplashActivity contributeSplashActivity();
}
