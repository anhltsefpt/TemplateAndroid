package anhtuan.com.musicstreaming.repository;

import anhtuan.com.musicstreaming.network.AppExecutors;
import javax.inject.Inject;
import javax.inject.Singleton;

public class SplashRepository {
  AppExecutors executors;

  @Inject
  public SplashRepository(AppExecutors executors) {
    this.executors = executors;
  }
}
