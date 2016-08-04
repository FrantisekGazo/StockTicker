package eu.f3rog.stockticker.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

import blade.mvp.IView;
import eu.f3rog.stockticker.model.Symbol;

/**
 * Created by f3rog on 8/4/16.
 */
public interface ISymbolSelectionView extends IView {

    void show(@NonNull List<Symbol> symbols);

    void showError(@StringRes int msg);

    void clearInput();
}
