package io.github.ovso.drive.main.f_beer;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.drive.Security;
import io.github.ovso.drive.main.f_beer.adapter.BeerAdapter;
import io.github.ovso.drive.main.f_beer.adapter.BeerAdapterView;
import io.github.ovso.drive.main.f_beer.adapter.OnEndlessRecyclerScrollListener;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public class BeerFragmentModule {

  @Provides BeerPresenter provideBeerPresenter(BeerFragment fragment, BeerNetwork network) {
    return new BeerPresenterImpl(fragment, fragment.getAdapter(), network,
        fragment.getCompositeDisposable());
  }

  @Provides BeerAdapter provideBeerAdapter(BeerFragment fragment) {
    return new BeerAdapter().setOnRecyclerItemClickListener(fragment)
        .setCompositeDisposable(fragment.getCompositeDisposable());
  }

  @Provides BeerAdapterView provideBaseAdapterView(BeerFragment fragment) {
    return fragment.getAdapter();
  }

  @Provides BeerNetwork provideNetwork() {
    return new BeerNetwork(Security.BASE_URL.getValue());
  }

  @Provides CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides OnEndlessRecyclerScrollListener provideEndlessRecyclerScrollListener(
      BeerFragment fragment) {
    OnEndlessRecyclerScrollListener listener = new OnEndlessRecyclerScrollListener();
    listener.setOnLoadMoreListener(fragment);
    return listener;
  }
}
