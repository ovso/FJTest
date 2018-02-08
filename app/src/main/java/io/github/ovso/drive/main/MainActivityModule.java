package io.github.ovso.drive.main;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.github.ovso.drive.main.f_beer.BeerFragment;
import io.github.ovso.drive.main.f_beer.BeerFragmentModule;

/**
 * Created by jaeho on 2017. 10. 16
 */

@Module public abstract class MainActivityModule {
  @ContributesAndroidInjector(modules = BeerFragmentModule.class)
  abstract BeerFragment providePhoneFragmentFactory();

  @Provides static MainPresenter provideMainPresenter(MainActivity activity) {
    return new MainPresenterImpl(activity);
  }
}