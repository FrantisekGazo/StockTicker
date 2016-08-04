package eu.f3rog.stockticker.presenter;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import blade.mvp.BasePresenter;
import eu.f3rog.stockticker.di.Components;
import eu.f3rog.stockticker.model.Stock;
import eu.f3rog.stockticker.service.StockUpdater;
import eu.f3rog.stockticker.view.IStocksView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by f3rog on 8/4/16.
 */
public class StockPresenter
        extends BasePresenter<IStocksView, String> {

    @Inject
    StockUpdater mStockUpdater;

    private List<Stock> mLatest = Collections.emptyList();

    @Override
    public void create(String data, boolean wasRestored) {
        super.create(data, wasRestored);
        Components.getAppComponent().inject(this);

        mStockUpdater.start();
        loadStocks();
    }

    @Override
    public void destroy() {
        super.destroy();
        mStockUpdater.stop();
    }

    private void loadStocks() {
        mStockUpdater.observeStocks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Stock>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "onError: " + e);
                    }

                    @Override
                    public void onNext(List<Stock> stocks) {
                        mLatest = stocks;
                        if (getView() != null) {
                            getView().show(stocks);
                        }
                    }
                });
    }

    @Override
    public void bind(IStocksView view) {
        super.bind(view);

        view.show(mLatest);
    }
}
