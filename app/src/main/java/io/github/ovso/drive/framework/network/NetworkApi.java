package io.github.ovso.drive.framework.network;

import io.github.ovso.drive.main.f_beer.model.Beer;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jaeho on 2017. 12. 28
 */

public interface NetworkApi {

  @GET("/v2/beers") Single<List<Beer>> getBeers(
      @Query("page") int page, @Query("per_page") int per_page);

}