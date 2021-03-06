package eu.f3rog.stockticker.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import eu.f3rog.stockticker.model.Stock;
import eu.f3rog.stockticker.model.Symbol;
import eu.f3rog.stockticker.service.api.ApiService;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Class {@link StockUpdaterImpl}
 *
 * @author FrantisekGazo
 * @version 2016-08-04
 */
public final class StockUpdaterImpl
        implements StockUpdater {

    private static final String TAG = StockUpdaterImpl.class.getCanonicalName();
    private static final long INTERVAL = 10;

    private final DataService mDataService;
    private final ApiService mApi;
    private final BehaviorSubject<List<Stock>> mStocks;
    private Subscription mDisposable;

    public StockUpdaterImpl(DataService dataService, ApiService api) {
        mDataService = dataService;
        mApi = api;
        mStocks = BehaviorSubject.create();
    }

    @Override
    public Observable<List<Stock>> observeStocks() {
        return mStocks;
    }

    @Override
    public void start() {
        // preload with empty data
        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, List<Stock>>() {
                    @Override
                    public List<Stock> call(Integer i) {
                        List<Stock> list = new ArrayList<>();
                        for (Symbol sym : mDataService.getSymbols()) {
                            list.add(new Stock(sym.getName(), null));
                        }
                        return list;
                    }
                })
                .subscribe(new Action1<List<Stock>>() {
                    @Override
                    public void call(List<Stock> stocks) {
                        mStocks.onNext(stocks);
                        // start loading real data
                        startRequests();
                    }
                });
    }

    private void startRequests() {
        stop(); // make sure it's not already running

        mDisposable = Observable.interval(0, INTERVAL, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Long, List<Symbol>>() {
                    @Override
                    public List<Symbol> call(Long aLong) {
                        return mDataService.getSymbols();
                    }
                })
                .map(new Func1<List<Symbol>, List<Stock>>() {
                    @Override
                    public List<Stock> call(List<Symbol> symbols) {
                        return mApi.getStocks(symbols);
                    }
                })
                .subscribe(new Action1<List<Stock>>() {
                    @Override
                    public void call(List<Stock> stocks) {
                        mStocks.onNext(stocks);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable);
                    }
                });
    }

    @Override
    public void stop() {
        if (mDisposable != null && !mDisposable.isUnsubscribed()) {
            mDisposable.unsubscribe();
            mDisposable = null;
        }
    }
}
