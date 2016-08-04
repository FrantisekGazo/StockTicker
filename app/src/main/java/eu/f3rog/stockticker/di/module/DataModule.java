package eu.f3rog.stockticker.di.module;

import dagger.Module;
import dagger.Provides;
import eu.f3rog.stockticker.di.AppScope;
import eu.f3rog.stockticker.service.DataService;
import eu.f3rog.stockticker.service.DataServiceMock;
import eu.f3rog.stockticker.service.StockUpdater;
import eu.f3rog.stockticker.service.StockUpdaterImpl;

@Module
public final class DataModule {

    @Provides
    @AppScope
    public DataService providesDataFileService() {
        return new DataServiceMock();
    }

    @Provides
    @AppScope
    public StockUpdater providesStockUpdater(DataService dataService) {
        return new StockUpdaterImpl(dataService);
    }
}
