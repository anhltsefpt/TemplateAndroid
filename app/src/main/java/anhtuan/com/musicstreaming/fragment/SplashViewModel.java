package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModel;
import anhtuan.com.musicstreaming.network.AppService;
import anhtuan.com.musicstreaming.repository.SplashRepository;
import javax.inject.Inject;

public class SplashViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  SplashRepository repository;
  AppService appService;

  @Inject
  public SplashViewModel(SplashRepository repository,
      AppService appService) {
    this.repository = repository;
    this.appService = appService;
  }
}
