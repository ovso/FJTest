package io.github.ovso.drive.main.f_beer;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.Constants;
import io.github.ovso.drive.framework.customview.BaseFragment;
import io.github.ovso.drive.framework.listener.OnRecyclerItemClickListener;
import io.github.ovso.drive.main.f_beer.adapter.BeerAdapter;
import io.github.ovso.drive.main.f_beer.adapter.BeerAdapterView;
import io.github.ovso.drive.main.f_beer.adapter.OnEndlessRecyclerScrollListener;
import io.github.ovso.drive.main.f_beer.model.Beer;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import lombok.Getter;

/**
 * Created by jaeho on 2017. 12. 28
 */
public class BeerFragment extends BaseFragment
    implements BeerPresenter.View, OnRecyclerItemClickListener<Beer>,
    OnEndlessRecyclerScrollListener.OnLoadMoreListener {
  @Getter @BindView(R.id.recyclerview) RecyclerView recyclerView;
  @BindView(R.id.progress_bar) ProgressBar progressBar;
  @Inject @Getter CompositeDisposable compositeDisposable;
  @Inject OnEndlessRecyclerScrollListener onEndlessRecyclerScrollListener;
  @Inject @Getter BeerAdapter adapter;
  @Inject @Getter BeerAdapterView adapterView;
  @Inject BeerPresenter presenter;

  @Inject public BeerFragment() {

  }

  @Override protected int getLayoutResID() {
    return R.layout.fragment_bear;
  }

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
    presenter.onActivityCreate();
  }

  public static BeerFragment newInstance() {
    BeerFragment f = new BeerFragment();
    return f;
  }

  @Override public void setRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    onEndlessRecyclerScrollListener.setLayoutManager(recyclerView.getLayoutManager());
    recyclerView.addOnScrollListener(onEndlessRecyclerScrollListener);
    ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
    animationAdapter.setDuration(Constants.DURATION_RECYCLERVIEW_ANI);
    animationAdapter.setFirstOnly(false);
    recyclerView.setAdapter(animationAdapter);
  }

  @Override public void showMessage(int resId) {
    Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
  }

  @Override public void showLoading() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void refreshStartToEnd(int startPosition) {
    adapterView.refreshToEnd(startPosition);
  }

  @DebugLog @Override public void onDetach() {
    super.onDetach();
    presenter.onDetach();
  }

  @Override public void onItemClick(Beer item) {
    presenter.onItemClick(item);
  }

  @Override public void onLoadMore() {
    presenter.onLoadMore();
  }

  @Override public void setLoaded() {
    onEndlessRecyclerScrollListener.setLoaded();
  }

  @Override public void notifyItemInserted(int position) {
    adapterView.notifyItemInserted(position);
  }

  @Override public void notifyItemRemoved(int position) {
    adapterView.notifyItemRemoved(position);
  }

  @Override public void notifyItemRangeInserted(int startPosition, int itemCount) {
    adapterView.notifyItemRangeInserted(startPosition, itemCount);
  }
}
