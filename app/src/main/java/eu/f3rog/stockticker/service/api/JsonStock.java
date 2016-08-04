package eu.f3rog.stockticker.service.api;

/**
 * Class {@link JsonStock}
 *
 * @author FrantisekGazo
 * @version 2016-08-04
 */
final class JsonStock {

    // response looks like this: "symbol":"AAPL","ask":"105.44","bid":"105.41","last_trade_date":"8/3/2016","low":"104.77","high":"105.84","low_52_weeks":"89.47","high_52_weeks":"123.82","volume":"30227593","open":"104.81","close":"105.79"

    String symbol;
    String ask;
}
