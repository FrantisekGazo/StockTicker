package eu.f3rog.stockticker.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by f3rog on 8/4/16.
 */
public final class Stock {

    @NonNull
    private final Symbol mSymbol;
    @Nullable
    private final Double mPrice;

    public Stock(@NonNull Symbol symbol, @Nullable Double price) {
        mSymbol = symbol;
        mPrice = price;
    }

    public Symbol getSymbol() {
        return mSymbol;
    }

    public Double getPrice() {
        return mPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (mSymbol != null ? !mSymbol.equals(stock.mSymbol) : stock.mSymbol != null) return false;
        return mPrice != null ? mPrice.equals(stock.mPrice) : stock.mPrice == null;

    }

    @Override
    public int hashCode() {
        int result = mSymbol != null ? mSymbol.hashCode() : 0;
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        return result;
    }

    public String getFormattedPrice() {
        return (mPrice != null) ? String.format("%.2f", mPrice) : "N/A";
    }
}
