package anhtuan.com.musicstreaming.repository;

import anhtuan.com.musicstreaming.network.AppExecutors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MoreRepository {
  AppExecutors executors;

  @Inject
  public MoreRepository(AppExecutors executors) {
    this.executors = executors;
  }
}
