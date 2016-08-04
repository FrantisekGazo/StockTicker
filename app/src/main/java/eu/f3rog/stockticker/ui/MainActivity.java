package eu.f3rog.stockticker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import blade.Blade;
import butterknife.Bind;
import eu.f3rog.stockticker.R;
import eu.f3rog.stockticker.view.ISymbolSelectionView;

/**
 * Created by f3rog on 8/4/16.
 */
@Blade
public class MainActivity
        extends BaseActivity {

    @Bind(R.id.view_symbols)
    ISymbolSelectionView mSymbolSelectionView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSymbolSelectionView.setTag("symbols");
    }
}
