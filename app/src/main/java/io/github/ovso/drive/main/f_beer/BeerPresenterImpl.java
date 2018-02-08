package io.github.ovso.drive.main.f_beer;

import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.drive.main.f_beer.model.Beer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class BeerPresenterImpl implements BeerPresenter {
  private BeerPresenter.View view;
  private BaseAdapterDataModel<Beer> adapterDataModel;
  private BeerNetwork network;
  private CompositeDisposable compositeDisposable;

  public BeerPresenterImpl(BeerPresenter.View view, BaseAdapterDataModel adapterDataModel,
      BeerNetwork network, CompositeDisposable compositeDisposable) {
    this.view = view;
    this.adapterDataModel = adapterDataModel;
    this.network = network;
    this.compositeDisposable = compositeDisposable;
  }

  private int page = 1;
  private int per_page = 30;

  @DebugLog @Override public void onActivityCreate() {
    view.showLoading();
    view.setRecyclerView();

    compositeDisposable.add(network.getResult(page, per_page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(items -> {
          adapterDataModel.addAll(items);
          view.refresh();
          view.hideLoading();
          view.setLoaded();
        }, throwable -> {
          view.hideLoading();
        }));
  }

  @Override public void onDetach() {
    compositeDisposable.clear();
  }

  @DebugLog @Override public void onItemClick(Beer item) {
  }

  @DebugLog @Override public void onLoadMore() {

    adapterDataModel.add(null);
    final int loadingPosition = adapterDataModel.getSize() - 1;
    view.notifyItemInserted(loadingPosition);
    page++;
    compositeDisposable.add(network.getResult(page, per_page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(items -> {
          adapterDataModel.remove(loadingPosition);
          view.notifyItemRemoved(loadingPosition);
          int oldSize = loadingPosition;
          adapterDataModel.addAll(items);
          view.notifyItemRangeInserted(oldSize, adapterDataModel.getSize());
          view.setLoaded();
        }, throwable -> {
          view.showMessage(R.string.error_server);
          view.hideLoading();
        }));
  }
}