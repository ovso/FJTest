package io.github.ovso.drive.framework;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import io.github.ovso.drive.R;
import javax.annotation.Nonnull;

/**
 * Created by jaeho on 2018. 2. 6
 */

public class ActivityUtils {

  public static void replaceFragment(@Nonnull FragmentManager fragmentManager,
      @Nonnull Fragment fragment, int containerViewId) {
    fragmentManager.beginTransaction()
        .setCustomAnimations(R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation)
        .replace(containerViewId, fragment)
        .commit();
  }
}
