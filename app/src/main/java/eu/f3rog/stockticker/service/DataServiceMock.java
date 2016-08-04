package eu.f3rog.stockticker.service;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import eu.f3rog.stockticker.model.Symbol;

/**
 * Class {@link DataServiceMock}
 *
 * @author FrantisekGazo
 * @version 2016-08-04
 */
public final class DataServiceMock implements DataService {

    private List<Symbol> mSymbols = new ArrayList<>();

    @Override
    public void storeSymbol(@NonNull Symbol symbol) {
        mSymbols.add(symbol);
    }

    @NonNull
    @Override
    public List<Symbol> getSymbols() {
        return mSymbols;
    }
}
