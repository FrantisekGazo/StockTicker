package eu.f3rog.stockticker.di.component;

import dagger.Component;
import eu.f3rog.stockticker.di.AppScope;
import eu.f3rog.stockticker.di.module.AppModule;
import eu.f3rog.stockticker.di.module.DataModule;
import eu.f3rog.stockticker.presenter.SymbolSelectionPresenter;

@AppScope
@Component(
        modules = {
                AppModule.class,
                DataModule.class,
        }
)
public abstract class AppComponent {

    // presenters

    public abstract void inject(SymbolSelectionPresenter presenter);
}

