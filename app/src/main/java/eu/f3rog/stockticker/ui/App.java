package eu.f3rog.stockticker.ui;

import android.app.Application;

import eu.f3rog.stockticker.di.Components;

public final class App
        extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Components.initAppComponent(this);
    }
}
