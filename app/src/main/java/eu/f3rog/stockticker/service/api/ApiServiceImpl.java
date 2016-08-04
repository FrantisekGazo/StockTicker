package eu.f3rog.stockticker.service.api;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.f3rog.stockticker.model.Stock;
import eu.f3rog.stockticker.model.Symbol;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Class {@link ApiServiceImpl}
 *
 * @author FrantisekGazo
 * @version 2016-08-04
 */
public final class ApiServiceImpl
        implements ApiService {

    private static final String TOKEN = "-jNAVTYMD1Ghuo18X7UM";

    private final YahooFinanceApi mYahooFinanceApi;

    public ApiServiceImpl(YahooFinanceApi yahooFinanceApi) {
        mYahooFinanceApi = yahooFinanceApi;
    }

    @Override
    @NonNull
    public List<Stock> getStocks(@NonNull List<Symbol> symbols) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < symbols.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(symbols.get(i).getName());
        }

        Call<List<JsonStock>> call = mYahooFinanceApi.getStocks(TOKEN, sb.toString());

        try {
            Response<List<JsonStock>> response = call.execute();
            List<JsonStock> jsonStocks = response.body();
            List<Stock> stocks = new ArrayList<>();
            if (jsonStocks != null) {
                for (JsonStock json : jsonStocks) {
                    stocks.add(new Stock(json.symbol, json.ask));
                }
            }
            return stocks;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
