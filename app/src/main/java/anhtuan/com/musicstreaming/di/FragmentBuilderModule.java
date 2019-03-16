package anhtuan.com.musicstreaming.di;

import anhtuan.com.musicstreaming.fragment.MoreFragment;
import anhtuan.com.musicstreaming.fragment.MyLibraryFragment;
import anhtuan.com.musicstreaming.fragment.PlayListFragment;
import anhtuan.com.musicstreaming.fragment.SearchFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
  @ContributesAndroidInjector
  abstract MyLibraryFragment contributeMyLibraryFragment();

  @ContributesAndroidInjector
  abstract PlayListFragment contributePlayListFragment();

  @ContributesAndroidInjector
  abstract SearchFragment contributeSearchFragment();

  @ContributesAndroidInjector
  abstract MoreFragment contributeMoreFragment();
}
