package anhtuan.com.musicstreaming.repository;

import anhtuan.com.musicstreaming.network.AppExecutors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyLibraryRepository {
  AppExecutors executors;

  @Inject
  public MyLibraryRepository(AppExecutors executors) {
    this.executors = executors;
  }
}
