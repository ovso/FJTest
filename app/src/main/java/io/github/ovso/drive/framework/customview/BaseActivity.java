package io.github.ovso.drive.framework.customview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.ovso.drive.R;

/**
 * Created by jaeho on 2017. 10. 19
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {
  private Unbinder unbinder;
  protected @BindView(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    unbinder = ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    setNavigationBarColor();
  }

  private void setNavigationBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  protected abstract int getLayoutResId();

}
