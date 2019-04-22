package anhtuan.com.musicstreaming.repository;

import anhtuan.com.musicstreaming.network.AppExecutors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayListRepository {
  AppExecutors appExecutors;

  @Inject
  public PlayListRepository(AppExecutors appExecutors) {
    this.appExecutors = appExecutors;
  }
}
