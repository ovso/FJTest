package io.github.ovso.drive.main;

import android.os.Bundle;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.ActivityUtils;
import io.github.ovso.drive.framework.customview.BaseActivity;
import io.github.ovso.drive.main.f_beer.BeerFragment;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainPresenter.View {

  @Inject MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate(savedInstanceState);
  }

  @Override public void changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar);
  }

  @Override public void showPhoneFragment() {
    ActivityUtils.replaceFragment(getSupportFragmentManager(), BeerFragment.newInstance(),
        R.id.fragment_container);
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}