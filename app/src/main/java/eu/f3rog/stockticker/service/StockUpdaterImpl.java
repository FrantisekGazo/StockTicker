package eu.f3rog.stockticker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import eu.f3rog.stockticker.model.Stock;
import eu.f3rog.stockticker.model.Symbol;
import rx.Observable;
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
public class StockUpdaterImpl
        implements StockUpdater {

    private static final long INTERVAL = 300;

    private final DataService mDataService;
    private final BehaviorSubject<List<Stock>> mStocks;
    private Boolean mRunning = false;

    public StockUpdaterImpl(DataService dataService) {
        mDataService = dataService;
        mStocks = BehaviorSubject.create();
        load();
    }

    private void load() {
        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, List<Stock>>() {
                    @Override
                    public List<Stock> call(Integer i) {
                        List<Stock> list = new ArrayList<>();
                        for (Symbol sym : mDataService.getSymbols()) {
                            list.add(new Stock(sym, null));
                        }
                        return list;
                    }
                })
                .subscribe(new Action1<List<Stock>>() {
                    @Override
                    public void call(List<Stock> stocks) {
                        mStocks.onNext(stocks);
                    }
                });

        Observable.timer(INTERVAL, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .filter(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        return mRunning;
                    }
                })
                .map(new Func1<Long, List<Symbol>>() {
                    @Override
                    public List<Symbol> call(Long aLong) {
                        return mDataService.getSymbols();
                    }
                })
                .map(new Func1<List<Symbol>, List<Stock>>() {
                    @Override
                    public List<Stock> call(List<Symbol> symbols) {
                        List<Stock> list = new ArrayList<>();
                        for (Symbol sym : symbols) {
                            list.add(new Stock(sym, null));
                        }
                        return list;
                    }
                })
                .subscribe(new Action1<List<Stock>>() {
                    @Override
                    public void call(List<Stock> stocks) {
                        mStocks.onNext(stocks);
                    }
                });
    }

    @Override
    public Observable<List<Stock>> observeStocks() {
        return mStocks;
    }

    @Override
    public void start() {
        mRunning = true;
    }

    @Override
    public void stop() {
        mRunning = false;
    }
}
