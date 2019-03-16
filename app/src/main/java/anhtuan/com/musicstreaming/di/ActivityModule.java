package anhtuan.com.musicstreaming.di;

import anhtuan.com.musicstreaming.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
  @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
  abstract MainActivity contributeActivity();
}
