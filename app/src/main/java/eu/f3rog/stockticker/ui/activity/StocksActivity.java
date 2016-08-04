package eu.f3rog.stockticker.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import blade.Blade;
import butterknife.Bind;
import eu.f3rog.stockticker.R;
import eu.f3rog.stockticker.view.IStocksView;

/**
 * Created by f3rog on 8/4/16.
 */
@Blade
public class StocksActivity
        extends BaseActivity {

    @Bind(R.id.view_stacks)
    IStocksView mStocksView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_stocks;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStocksView.setTag("stocks");

        showBackButton();
    }
}
