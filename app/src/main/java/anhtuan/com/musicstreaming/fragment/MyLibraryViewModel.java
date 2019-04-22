package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModel;
import anhtuan.com.musicstreaming.repository.MyLibraryRepository;
import javax.inject.Inject;

public class MyLibraryViewModel extends ViewModel {
  // TODO: Implement the ViewModel

  MyLibraryRepository repository;

  @Inject
  public MyLibraryViewModel(MyLibraryRepository repository) {
    this.repository = repository;
  }
}
