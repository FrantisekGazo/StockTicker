package eu.f3rog.stockticker.di.module;

import dagger.Module;
import dagger.Provides;
import eu.f3rog.stockticker.di.AppScope;
import eu.f3rog.stockticker.service.api.ApiService;
import eu.f3rog.stockticker.service.api.ApiServiceImpl;
import eu.f3rog.stockticker.service.DataService;
import eu.f3rog.stockticker.service.DataServiceMock;
import eu.f3rog.stockticker.service.StockUpdater;
import eu.f3rog.stockticker.service.StockUpdaterImpl;
import eu.f3rog.stockticker.service.api.YahooFinanceApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class DataModule {

    @Provides
    @AppScope
    public DataService providesDataFileService() {
        return new DataServiceMock();
    }

    @Provides
    @AppScope
    public StockUpdater providesStockUpdater(DataService dataService, ApiService api) {
        return new StockUpdaterImpl(dataService, api);
    }

    @Provides
    public YahooFinanceApi providesYahooApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YahooFinanceApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(YahooFinanceApi.class);
    }

    @Provides
    @AppScope
    public ApiService providesApiService(YahooFinanceApi api) {
        return new ApiServiceImpl(api);
    }
}
