package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModel;
import anhtuan.com.musicstreaming.repository.MoreRepository;
import javax.inject.Inject;

public class MoreViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  MoreRepository repository;

  @Inject
  public MoreViewModel(MoreRepository repository) {
    this.repository = repository;
  }
}
