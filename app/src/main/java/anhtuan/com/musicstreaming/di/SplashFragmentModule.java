package anhtuan.com.musicstreaming.di;

import anhtuan.com.musicstreaming.fragment.SplashFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashFragmentModule {
  @ContributesAndroidInjector
  abstract SplashFragment contributeSplashFragment();
}
