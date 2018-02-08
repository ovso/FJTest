package io.github.ovso.drive.main.f_beer;

import android.support.annotation.StringRes;
import io.github.ovso.drive.main.f_beer.model.Beer;

/**
 * Created by jaeho on 2017. 11. 27
 */

public interface BeerPresenter {

  void onActivityCreate();

  void onDetach();

  void onItemClick(Beer item);

  void onLoadMore();

  interface View {

    void setRecyclerView();

    void showMessage(@StringRes int resId);

    void refresh();

    void showLoading();

    void hideLoading();

    void refreshStartToEnd(int position);

    void setLoaded();

    void notifyItemInserted(int position);

    void notifyItemRemoved(int position);

    void notifyItemRangeInserted(int startPosition, int itemCount);
  }
}
