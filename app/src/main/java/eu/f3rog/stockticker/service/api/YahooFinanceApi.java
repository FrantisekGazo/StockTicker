package eu.f3rog.stockticker.service.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by f3rog on 8/4/16.
 */
public interface YahooFinanceApi {

    String ENDPOINT = "https://www.enclout.com/api/v1/yahoo_finance/";

    @GET("show.json")
    Call<List<JsonStock>> getStocks(@Query("auth_token") String token, @Query("text") String symbols);
}
