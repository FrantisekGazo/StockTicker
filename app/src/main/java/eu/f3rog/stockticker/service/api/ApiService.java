package eu.f3rog.stockticker.service.api;

import android.support.annotation.NonNull;

import java.util.List;

import eu.f3rog.stockticker.model.Stock;
import eu.f3rog.stockticker.model.Symbol;

/**
 * Created by f3rog on 8/4/16.
 */
public interface ApiService {

    @NonNull
    List<Stock> getStocks(@NonNull List<Symbol> symbols);
}
