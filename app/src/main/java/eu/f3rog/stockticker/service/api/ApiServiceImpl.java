package eu.f3rog.stockticker.service.api;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> stocks = new HashMap<>();
        try {
            Response<List<JsonStock>> response = call.execute();
            List<JsonStock> jsonStocks = response.body();

            if (jsonStocks != null) {
                for (JsonStock json : jsonStocks) {
                    stocks.put(json.symbol, json.ask);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Stock> result = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            String symbolName = symbols.get(i).getName();
            String price = stocks.get(symbolName);
            Stock stock = new Stock(symbolName, price);
            result.add(stock);
        }
        return result;
    }
}
