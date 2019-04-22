package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModel;
import anhtuan.com.musicstreaming.repository.PlayListRepository;
import javax.inject.Inject;

public class PlayListViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  PlayListRepository repository;

  @Inject
  public PlayListViewModel(PlayListRepository repository) {
    this.repository = repository;
  }
}
