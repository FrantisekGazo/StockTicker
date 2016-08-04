package eu.f3rog.stockticker.service;

import android.support.annotation.NonNull;

import java.util.List;

import eu.f3rog.stockticker.model.Symbol;

/**
 * Created by f3rog on 8/4/16.
 */
public interface DataService {

    void storeSymbol(@NonNull Symbol symbol);

    @NonNull
    List<Symbol> getSymbols();
}
