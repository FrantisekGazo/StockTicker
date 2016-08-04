package eu.f3rog.stockticker.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Application providesApplication() {
        return mApplication;
    }

    @Provides
    public Context providesApplicationContext() {
        return mApplication;
    }
}
