package io.github.ovso.drive.main.f_beer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.adapter.BaseRecyclerAdapter;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class BeerViewHolder extends BaseRecyclerAdapter.BaseViewHolder {

  @BindView(R.id.name_textview) TextView nameTextView;
  @BindView(R.id.imageview) ImageView imageView;

  public BeerViewHolder(View itemView) {
    super(itemView);
  }
}