package io.github.ovso.drive.main;

import android.os.Bundle;

/**
 * Created by jaeho on 2017. 10. 16
 */

public interface MainPresenter {

  void onCreate(Bundle savedInstanceState);

  interface View {

    void finish();

    void showPhoneFragment();

    void changeTheme();
  }
}
