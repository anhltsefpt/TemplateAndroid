package anhtuan.com.musicstreaming.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import anhtuan.com.musicstreaming.fragment.MoreViewModel;
import anhtuan.com.musicstreaming.fragment.MyLibraryViewModel;
import anhtuan.com.musicstreaming.fragment.PlayListViewModel;
import anhtuan.com.musicstreaming.fragment.SearchViewModel;
import anhtuan.com.musicstreaming.fragment.SplashViewModel;
import anhtuan.com.musicstreaming.viewmodel.AppViewModelFactory;
import anhtuan.com.musicstreaming.viewmodel.ListTrackViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);

  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel.class)
  abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MyLibraryViewModel.class)
  abstract ViewModel bindMyLibraryViewModel(MyLibraryViewModel myLibraryViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(SearchViewModel.class)
  abstract ViewModel bindSearchViewModel(SearchViewModel searchViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MoreViewModel.class)
  abstract ViewModel bindMoreViewModel(MoreViewModel moreViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(PlayListViewModel.class)
  abstract ViewModel bindPlayListViewModel(PlayListViewModel playListViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(ListTrackViewModel.class)
  abstract ViewModel bindListTrackViewModel(ListTrackViewModel listTrackViewModel);
}
