package anhtuan.com.musicstreaming.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

  @Inject
  public ViewModelProvider.Factory viewModelFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}

