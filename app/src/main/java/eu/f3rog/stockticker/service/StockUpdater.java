package eu.f3rog.stockticker.service;

import java.util.List;

import eu.f3rog.stockticker.model.Stock;
import rx.Observable;

/**
 * Created by f3rog on 8/4/16.
 */

public interface StockUpdater {

    Observable<List<Stock>> observeStocks();

    void start();

    void stop();

}
