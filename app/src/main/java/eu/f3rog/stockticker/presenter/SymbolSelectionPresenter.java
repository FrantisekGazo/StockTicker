package eu.f3rog.stockticker.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import blade.mvp.BasePresenter;
import eu.f3rog.stockticker.R;
import eu.f3rog.stockticker.di.Components;
import eu.f3rog.stockticker.model.Symbol;
import eu.f3rog.stockticker.service.DataService;
import eu.f3rog.stockticker.view.ISymbolSelectionView;

/**
 * Class {@link SymbolSelectionPresenter}
 *
 * @author FrantisekGazo
 * @version 2016-08-04
 */
public final class SymbolSelectionPresenter
        extends BasePresenter<ISymbolSelectionView, String> {

    @Inject
    DataService mDataService;

    @Override
    public void create(String data, boolean wasRestored) {
        super.create(data, wasRestored);
        Components.getAppComponent().inject(this);
    }

    @Override
    public void bind(ISymbolSelectionView view) {
        super.bind(view);

        List<Symbol> symbols = mDataService.getSymbols();
        view.show(symbols);
    }

    public void onAddClicked(@NonNull String name) {
        if (name.isEmpty()) {
            if (getView() != null) {
                getView().showError(R.string.err_empty);
            }
            return;
        }
        mDataService.storeSymbol(new Symbol(name));

        if (getView() != null) {
            List<Symbol> symbols = mDataService.getSymbols();
            getView().show(symbols);
            getView().clearInput();
        }
    }
}
