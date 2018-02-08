package io.github.ovso.drive.main.f_beer;

import hugo.weaving.DebugLog;
import io.github.ovso.drive.framework.network.NetworkHelper;
import io.github.ovso.drive.main.f_beer.model.Beer;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class BeerNetwork extends NetworkHelper {
  public BeerNetwork(String baseUrl) {
    super(baseUrl);
  }

  @DebugLog public Single<List<Beer>> getResult(int page, int per_page) {
    return getNetworkApi().getBeers(page, per_page);
  }
}
