package io.github.ovso.drive.main.f_beer.adapter;

import android.content.Context;
import android.view.View;
import com.jakewharton.rxbinding2.view.RxView;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.GlideApp;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.drive.framework.adapter.BaseRecyclerAdapter;
import io.github.ovso.drive.framework.listener.OnRecyclerItemClickListener;
import io.github.ovso.drive.main.f_beer.model.Beer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class BeerAdapter extends BaseRecyclerAdapter
    implements BeerAdapterView, BaseAdapterDataModel<Beer> {
  private int VIEW_TYPE_DEFAULT = 0;
  private int VIEW_TYPE_LOADING = 1;
  private List<Beer> items = new ArrayList<>();

  @Accessors(chain = true) @Setter private OnRecyclerItemClickListener<Beer>
      onRecyclerItemClickListener;

  @Accessors(chain = true) private @Setter CompositeDisposable compositeDisposable;

  @Override protected BaseViewHolder createViewHolder(View view, int viewType) {
    if (viewType == VIEW_TYPE_DEFAULT) {
      return new BeerViewHolder(view);
    } else {
      return new LoadingViewHolder(view);
    }
  }

  @Override public int getLayoutRes(int viewType) {
    if (viewType == VIEW_TYPE_DEFAULT) {
      return R.layout.fragment_bear_item;
    } else {
      return R.layout.loading_footer;
    }
  }

  @Override public int getItemViewType(int position) {
    if (getItem(position) != null) {
      return VIEW_TYPE_DEFAULT;
    } else {
      return VIEW_TYPE_LOADING;
    }
  }

  @Override public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    Context context = viewHolder.itemView.getContext();
    if (viewHolder instanceof BeerViewHolder) {
      Beer item = this.items.get(position);
      BeerViewHolder holder = (BeerViewHolder) viewHolder;
      holder.nameTextView.setText(item.getName());
      GlideApp.with(context).load(item.getImage_url()).into(holder.imageView);
      compositeDisposable.add(RxView.clicks(holder.itemView)
          .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(o -> onRecyclerItemClickListener.onItemClick(item)));
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(Beer item) {
    items.add(item);
  }

  @Override public void addAll(List<Beer> items) {
    this.items.addAll(items);
  }

  @Override public Beer remove(int position) {
    return this.items.remove(position);
  }

  @Override public Beer getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, Beer item) {
    this.items.add(index, item);
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyItemRangeChanged(0, getSize());
  }

  @Override public void refreshToEnd(int start) {
    notifyItemRangeChanged(start, getSize());
    //notifyItemRangeInserted(start, getSize());
  }
}