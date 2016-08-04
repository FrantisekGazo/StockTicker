package eu.f3rog.stockticker.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by f3rog on 8/4/16.
 */
public final class Stock {

    @NonNull
    private final String mSymbol;
    @Nullable
    private final String mPrice;

    public Stock(@NonNull String symbol, @Nullable String price) {
        mSymbol = symbol;
        mPrice = price;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getPrice() {
        return mPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (!mSymbol.equals(stock.mSymbol)) return false;
        return mPrice != null ? mPrice.equals(stock.mPrice) : stock.mPrice == null;

    }

    @Override
    public int hashCode() {
        int result = mSymbol.hashCode();
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        return result;
    }

    public String getFormattedPrice() {
        return (mPrice == null || mPrice.isEmpty()) ? "N/A" : mPrice;
    }
}
