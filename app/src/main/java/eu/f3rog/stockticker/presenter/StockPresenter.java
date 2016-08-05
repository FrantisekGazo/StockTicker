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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by f3rog on 8/4/16.
 */
public final class StockPresenter
        extends BasePresenter<IStocksView, String> {

    private static final String TAG = StockPresenter.class.getCanonicalName();

    @Inject
    StockUpdater mStockUpdater;

    private List<Stock> mLatest = Collections.emptyList();
    private Subscription mSubscription;

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
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
        mStockUpdater.stop();
    }

    private void loadStocks() {
        mSubscription = mStockUpdater.observeStocks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Stock>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
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
