package eu.f3rog.stockticker.di.module;

import dagger.Module;
import dagger.Provides;
import eu.f3rog.stockticker.service.DataService;
import eu.f3rog.stockticker.service.DataServiceMock;

@Module
public final class DataModule {

    @Provides
    public DataService providesDataFileService() {
        return new DataServiceMock();
    }
}
