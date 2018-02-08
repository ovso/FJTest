package io.github.ovso.drive.main;

import android.os.Bundle;
import javax.inject.Inject;

/**
 * Created by jaeho on 2017. 10. 16
 */

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;

  @Inject MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
    view.changeTheme();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    view.showPhoneFragment();
  }
}
