package anhtuan.com.musicstreaming.repository;

import anhtuan.com.musicstreaming.network.AppExecutors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchRepository {
  AppExecutors executors;

  @Inject
  public SearchRepository(AppExecutors executors) {
    this.executors = executors;
  }
}
