package anhtuan.com.musicstreaming.viewmodel;

import android.arch.lifecycle.ViewModel;
import anhtuan.com.musicstreaming.repository.ListTrackRepository;
import javax.inject.Inject;

public class ListTrackViewModel extends ViewModel {
  ListTrackRepository repository;

  @Inject
  public ListTrackViewModel(ListTrackRepository repository) {
    this.repository = repository;
  }
}
