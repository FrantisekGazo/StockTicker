package eu.f3rog.stockticker.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

import blade.mvp.IView;
import eu.f3rog.stockticker.model.Stock;

/**
 * Created by f3rog on 8/4/16.
 */
public interface IStocksView
        extends IView {

    void show(@NonNull List<Stock> stocks);

    void showError(@StringRes int msg);
}
