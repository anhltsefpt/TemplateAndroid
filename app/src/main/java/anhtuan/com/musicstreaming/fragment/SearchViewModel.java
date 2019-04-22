package anhtuan.com.musicstreaming.fragment;

import android.arch.lifecycle.ViewModel;
import anhtuan.com.musicstreaming.repository.SearchRepository;
import javax.inject.Inject;

public class SearchViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  SearchRepository repository;

  @Inject
  public SearchViewModel(SearchRepository repository) {
    this.repository = repository;
  }
}
